package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{Json, JsValue, OFormat}
import services.CalendarService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CalendarController @Inject()(cc: ControllerComponents, calendarService: CalendarService)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  import calendarService.{CreateEventRequest, CalendarEvent, createEventRequestReads}

  private def getAccessToken(request: play.api.mvc.Request[_]): Option[String] = {
    request.session.get("gmail_access_token") // Reuse Gmail OAuth token for Calendar API
  }

  def listEvents = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        val calendarId = request.getQueryString("calendarId").getOrElse("primary")
        val maxResults = request.getQueryString("maxResults").map(_.toInt).getOrElse(50)
        
        calendarService.listEvents(accessToken, calendarId, maxResults)
          .map { events =>
            Ok(Json.toJson(events.map { event =>
              Json.obj(
                "id" -> event.id,
                "summary" -> event.summary,
                "description" -> event.description,
                "start" -> Json.obj(
                  "dateTime" -> event.start.dateTime,
                  "timeZone" -> event.start.timeZone
                ),
                "end" -> Json.obj(
                  "dateTime" -> event.end.dateTime,
                  "timeZone" -> event.end.timeZone
                ),
                "attendees" -> event.attendees.map { attendee =>
                  Json.obj(
                    "email" -> attendee.email,
                    "displayName" -> attendee.displayName,
                    "responseStatus" -> attendee.responseStatus
                  )
                },
                "location" -> event.location,
                "status" -> event.status,
                "htmlLink" -> event.htmlLink
              )
            }))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to list events: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Google Calendar not connected. Please authenticate first."))
        )
    }
  }

  def getEvent(eventId: String) = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        val calendarId = request.getQueryString("calendarId").getOrElse("primary")
        
        calendarService.getEvent(accessToken, eventId, calendarId)
          .map { event =>
            Ok(Json.obj(
              "id" -> event.id,
              "summary" -> event.summary,
              "description" -> event.description,
              "start" -> Json.obj(
                "dateTime" -> event.start.dateTime,
                "timeZone" -> event.start.timeZone
              ),
              "end" -> Json.obj(
                "dateTime" -> event.end.dateTime,
                "timeZone" -> event.end.timeZone
              ),
              "attendees" -> event.attendees.map { attendee =>
                Json.obj(
                  "email" -> attendee.email,
                  "displayName" -> attendee.displayName,
                  "responseStatus" -> attendee.responseStatus
                )
              },
              "location" -> event.location,
              "status" -> event.status,
              "htmlLink" -> event.htmlLink
            ))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to get event: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Google Calendar not connected. Please authenticate first."))
        )
    }
  }

  def createEvent = Action.async(parse.json) { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        request.body.validate[CreateEventRequest].fold(
          _ => scala.concurrent.Future.successful(
            BadRequest(Json.obj("error" -> "Invalid event request payload"))
          ),
          eventRequest => {
            val calendarId = request.getQueryString("calendarId").getOrElse("primary")
            
            calendarService.createEvent(accessToken, eventRequest, calendarId)
              .map { createdEvent =>
                Ok(Json.obj(
                  "status" -> "created",
                  "event" -> Json.obj(
                    "id" -> createdEvent.id,
                    "summary" -> createdEvent.summary,
                    "description" -> createdEvent.description,
                    "start" -> Json.obj(
                      "dateTime" -> createdEvent.start.dateTime,
                      "timeZone" -> createdEvent.start.timeZone
                    ),
                    "end" -> Json.obj(
                      "dateTime" -> createdEvent.end.dateTime,
                      "timeZone" -> createdEvent.end.timeZone
                    ),
                    "attendees" -> createdEvent.attendees.map { attendee =>
                      Json.obj(
                        "email" -> attendee.email,
                        "displayName" -> attendee.displayName,
                        "responseStatus" -> attendee.responseStatus
                      )
                    },
                    "location" -> createdEvent.location,
                    "htmlLink" -> createdEvent.htmlLink
                  )
                ))
              }
              .recover {
                case exception => 
                  InternalServerError(Json.obj("error" -> s"Failed to create event: ${exception.getMessage}"))
              }
          }
        )
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Google Calendar not connected. Please authenticate first."))
        )
    }
  }

  def sendInvitations(eventId: String) = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        val calendarId = request.getQueryString("calendarId").getOrElse("primary")
        
        calendarService.sendInvitations(accessToken, eventId, calendarId)
          .map { success =>
            if (success) {
              Ok(Json.obj(
                "status" -> "sent",
                "message" -> "Invitations sent successfully"
              ))
            } else {
              InternalServerError(Json.obj("error" -> "Failed to send invitations"))
            }
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to send invitations: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Google Calendar not connected. Please authenticate first."))
        )
    }
  }

  def listCalendars = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        calendarService.listCalendars(accessToken)
          .map { calendars =>
            Ok(Json.obj("calendars" -> calendars))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to list calendars: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Google Calendar not connected. Please authenticate first."))
        )
    }
  }

  def getConnectionStatus = Action { request =>
    val isConnected = getAccessToken(request).isDefined
    Ok(Json.obj(
      "connected" -> isConnected,
      "hasRefreshToken" -> request.session.get("gmail_refresh_token").exists(_.nonEmpty)
    ))
  }
}
