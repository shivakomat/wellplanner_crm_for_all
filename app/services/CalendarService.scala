package services

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.ws.WSClient
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId}

@Singleton
class CalendarService @Inject()(ws: WSClient, config: Configuration)(implicit ec: ExecutionContext) {
  
  private val CALENDAR_API_BASE = "https://www.googleapis.com/calendar/v3"

  case class CalendarEvent(
    id: Option[String] = None,
    summary: String,
    description: Option[String] = None,
    start: EventDateTime,
    end: EventDateTime,
    attendees: List[Attendee] = List.empty,
    location: Option[String] = None,
    status: Option[String] = None,
    htmlLink: Option[String] = None
  )

  case class EventDateTime(
    dateTime: String,
    timeZone: String = "America/Los_Angeles"
  )

  case class Attendee(
    email: String,
    displayName: Option[String] = None,
    responseStatus: Option[String] = None
  )

  case class CreateEventRequest(
    summary: String,
    description: Option[String] = None,
    startDateTime: String,
    endDateTime: String,
    attendeeEmails: List[String] = List.empty,
    location: Option[String] = None,
    sendNotifications: Boolean = true
  )

  // JSON formatters
  implicit val eventDateTimeReads: Reads[EventDateTime] = Json.reads[EventDateTime]
  implicit val eventDateTimeWrites: Writes[EventDateTime] = Json.writes[EventDateTime]
  implicit val attendeeReads: Reads[Attendee] = Json.reads[Attendee]
  implicit val attendeeWrites: Writes[Attendee] = Json.writes[Attendee]
  implicit val calendarEventReads: Reads[CalendarEvent] = Json.reads[CalendarEvent]
  implicit val calendarEventWrites: Writes[CalendarEvent] = Json.writes[CalendarEvent]
  implicit val createEventRequestReads: Reads[CreateEventRequest] = Json.reads[CreateEventRequest]
  implicit val createEventRequestWrites: Writes[CreateEventRequest] = Json.writes[CreateEventRequest]

  def listEvents(accessToken: String, calendarId: String = "primary", maxResults: Int = 50): Future[List[CalendarEvent]] = {
    val url = s"$CALENDAR_API_BASE/calendars/$calendarId/events"
    val queryParams = Map(
      "maxResults" -> maxResults.toString,
      "singleEvents" -> "true",
      "orderBy" -> "startTime"
    )

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .addQueryStringParameters(queryParams.toSeq: _*)
      .get()
      .map { response =>
        if (response.status == 200) {
          (response.json \ "items").asOpt[List[JsValue]].getOrElse(List.empty)
            .map { eventJson =>
              CalendarEvent(
                id = (eventJson \ "id").asOpt[String],
                summary = (eventJson \ "summary").asOpt[String].getOrElse(""),
                description = (eventJson \ "description").asOpt[String],
                start = parseEventDateTime(eventJson \ "start"),
                end = parseEventDateTime(eventJson \ "end"),
                attendees = parseAttendees(eventJson \ "attendees"),
                location = (eventJson \ "location").asOpt[String],
                status = (eventJson \ "status").asOpt[String],
                htmlLink = (eventJson \ "htmlLink").asOpt[String]
              )
            }
        } else {
          throw new RuntimeException(s"Calendar API error: ${response.status} - ${response.body}")
        }
      }
  }

  def createEvent(accessToken: String, eventRequest: CreateEventRequest, calendarId: String = "primary"): Future[CalendarEvent] = {
    val url = s"$CALENDAR_API_BASE/calendars/$calendarId/events"
    
    val attendees = eventRequest.attendeeEmails.map { email =>
      Json.obj("email" -> email)
    }

    val eventBody = Json.obj(
      "summary" -> eventRequest.summary,
      "start" -> Json.obj(
        "dateTime" -> eventRequest.startDateTime,
        "timeZone" -> "America/Los_Angeles"
      ),
      "end" -> Json.obj(
        "dateTime" -> eventRequest.endDateTime,
        "timeZone" -> "America/Los_Angeles"
      )
    ) ++ 
    eventRequest.description.map(d => Json.obj("description" -> d)).getOrElse(Json.obj()) ++
    eventRequest.location.map(l => Json.obj("location" -> l)).getOrElse(Json.obj()) ++
    (if (attendees.nonEmpty) Json.obj("attendees" -> attendees) else Json.obj())

    val queryParams = if (eventRequest.sendNotifications) {
      Map("sendNotifications" -> "true")
    } else Map.empty[String, String]

    ws.url(url)
      .addHttpHeaders(
        "Authorization" -> s"Bearer $accessToken",
        "Content-Type" -> "application/json"
      )
      .addQueryStringParameters(queryParams.toSeq: _*)
      .post(eventBody)
      .map { response =>
        if (response.status == 200) {
          val eventJson = response.json
          CalendarEvent(
            id = (eventJson \ "id").asOpt[String],
            summary = (eventJson \ "summary").asOpt[String].getOrElse(""),
            description = (eventJson \ "description").asOpt[String],
            start = parseEventDateTime(eventJson \ "start"),
            end = parseEventDateTime(eventJson \ "end"),
            attendees = parseAttendees(eventJson \ "attendees"),
            location = (eventJson \ "location").asOpt[String],
            status = (eventJson \ "status").asOpt[String],
            htmlLink = (eventJson \ "htmlLink").asOpt[String]
          )
        } else {
          throw new RuntimeException(s"Calendar API error: ${response.status} - ${response.body}")
        }
      }
  }

  def getEvent(accessToken: String, eventId: String, calendarId: String = "primary"): Future[CalendarEvent] = {
    val url = s"$CALENDAR_API_BASE/calendars/$calendarId/events/$eventId"

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .get()
      .map { response =>
        if (response.status == 200) {
          val eventJson = response.json
          CalendarEvent(
            id = (eventJson \ "id").asOpt[String],
            summary = (eventJson \ "summary").asOpt[String].getOrElse(""),
            description = (eventJson \ "description").asOpt[String],
            start = parseEventDateTime(eventJson \ "start"),
            end = parseEventDateTime(eventJson \ "end"),
            attendees = parseAttendees(eventJson \ "attendees"),
            location = (eventJson \ "location").asOpt[String],
            status = (eventJson \ "status").asOpt[String],
            htmlLink = (eventJson \ "htmlLink").asOpt[String]
          )
        } else {
          throw new RuntimeException(s"Calendar API error: ${response.status} - ${response.body}")
        }
      }
  }

  def sendInvitations(accessToken: String, eventId: String, calendarId: String = "primary"): Future[Boolean] = {
    // This updates the event to send notifications to all attendees
    val url = s"$CALENDAR_API_BASE/calendars/$calendarId/events/$eventId"
    
    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .addQueryStringParameters("sendNotifications" -> "true")
      .patch(Json.obj()) // Empty patch just to trigger notifications
      .map { response =>
        response.status == 200
      }
  }

  def listCalendars(accessToken: String): Future[List[JsValue]] = {
    val url = s"$CALENDAR_API_BASE/users/me/calendarList"

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .get()
      .map { response =>
        if (response.status == 200) {
          (response.json \ "items").asOpt[List[JsValue]].getOrElse(List.empty)
        } else {
          throw new RuntimeException(s"Calendar API error: ${response.status} - ${response.body}")
        }
      }
  }

  private def parseEventDateTime(json: JsLookupResult): EventDateTime = {
    val dateTime = (json \ "dateTime").asOpt[String].getOrElse("")
    val timeZone = (json \ "timeZone").asOpt[String].getOrElse("America/Los_Angeles")
    EventDateTime(dateTime, timeZone)
  }

  private def parseAttendees(json: JsLookupResult): List[Attendee] = {
    json.asOpt[List[JsValue]].getOrElse(List.empty).map { attendeeJson =>
      Attendee(
        email = (attendeeJson \ "email").asOpt[String].getOrElse(""),
        displayName = (attendeeJson \ "displayName").asOpt[String],
        responseStatus = (attendeeJson \ "responseStatus").asOpt[String]
      )
    }
  }
}
