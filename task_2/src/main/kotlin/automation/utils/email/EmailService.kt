package automation.utils.email

import automation.configs.RecipientConfig
import automation.models.messsage.BaseMessageWithImage
import automation.utils.EnvironmentVariableDelegate
import automation.utils.logger
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailService {
    private val from: String by EnvironmentVariableDelegate("INTERVIEW_USER")
    private val password: String by EnvironmentVariableDelegate("INTERVIEW_PASS")

    private val session: Session

    init {
        val props = Properties()
        props["mail.smtp.host"] = "smtp.mail.yahoo.com"
        props["mail.smtp.port"] = "587"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.auth"] = "true"
        session = Session.getDefaultInstance(props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(from, password)
                }
            })
    }

    fun sendEmailTo(subject: String, messageWithImage: BaseMessageWithImage) {
        val to = RecipientConfig.to
            ?: throw RuntimeException("Couldn't get value from [to] system property. Please add string [-Dto='...'] in VM arguments")

        try {
            val message = MimeMessage(session).also {
                it.setFrom(from)
                    .also { logger().info("Email [from] field is set to [$from]") }
                it.addRecipient(Message.RecipientType.TO, InternetAddress(to))
                    .also { logger().info("Email recipient field is set to [$to]") }
                it.subject = subject
                    .also { logger().info("Email subject is set to [$subject]") }
            }

            message.setContent(messageWithImage.multipart)

            logger().info("Sending email")
            Transport.send(message)
            logger().info("Email was successfully sent")
        } catch (e: MessagingException) {
            logger().warn("Email wasn't send")
            e.printStackTrace()
            throw e
        }
    }
}