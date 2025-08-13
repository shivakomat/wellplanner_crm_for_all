package services

import javax.inject.Inject
import play.api.Configuration
import javax.mail.{Message, PasswordAuthentication, Session, Transport}
import javax.mail.internet.{InternetAddress, MimeMessage}

class EmailService @Inject()(config: Configuration) {

  private val host: String = config.getOptional[String]("mail.smtp.host").getOrElse("smtp.gmail.com")
  private val port: Int = config.getOptional[Int]("mail.smtp.port").getOrElse(587)
  private val user: String = config.getOptional[String]("mail.smtp.user").getOrElse("")
  private val pass: String = config.getOptional[String]("mail.smtp.pass").getOrElse("")
  private val from: String = config.getOptional[String]("mail.from").getOrElse("noreply@wellplanner.com")

  private lazy val session: Session = {
    val props = new java.util.Properties()
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", host)
    props.put("mail.smtp.port", port.toString)
    Session.getInstance(props, new javax.mail.Authenticator {
      override protected def getPasswordAuthentication: PasswordAuthentication =
        new PasswordAuthentication(user, pass)
    })
  }



  def sendEmail(to: String, subject: String, htmlBody: String, textBody: String = ""): Unit = {
    val message = new MimeMessage(session)
    message.setFrom(new InternetAddress(from))
    message.setRecipients(Message.RecipientType.TO, to)
    message.setSubject(subject)
    message.setContent(htmlBody, "text/html; charset=utf-8")
    // Optionally add plain text alternative
    if (textBody.nonEmpty) {
      // For simplicity, we ignore multipart alternative; most clients handle HTML
    }
    Transport.send(message)
  }
}
