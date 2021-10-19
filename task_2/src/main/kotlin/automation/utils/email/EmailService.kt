package automation.utils.email

import automation.configs.RecipientConfig
import automation.utils.EnvironmentVariableDelegate
import automation.utils.logger
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.activation.DataHandler
import javax.imageio.ImageIO
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

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
        logger().info("Creating email sending session")
        session = Session.getDefaultInstance(props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(from, password)
                }
            })
    }

    fun sendEmailTo(subject: String, text: String, image: BufferedImage) {
        val to = RecipientConfig.to
            ?: throw RuntimeException("Couldn't get value from [to] system property. Please add [-Dto='...'] in VM arguments")

        try {
            val message = MimeMessage(session).also {
                it.setFrom(from).also { logger().info("Email [from] field is set to [$from]") }
                it.addRecipient(Message.RecipientType.TO, InternetAddress(to))
                    .also { logger().info("Email recipient field is set to [$to]") }
                it.subject = subject.also { logger().info("Email subject is set to [$subject]") }
            }

            val multipart = MimeMultipart()

            val bodyPart = MimeBodyPart().also {
                it.setContent(text, "text/html")
            }.also {
                logger().info("Email text is set to [$text]")
                logger().info("Preparing body part for email")
            }

            val imagePart = prepareMessageBodyPartWithImage(image)

            multipart.addBodyPart(bodyPart)
            multipart.addBodyPart(imagePart)

            message.setContent(multipart)

            logger().info("Sending email")
            Transport.send(message)
            logger().info("Email was successfully sent")
        } catch (e: MessagingException) {
            logger().warn("Email wasn't send")
            e.printStackTrace()
            throw e
        }
    }

    private fun prepareMessageBodyPartWithImage(image: BufferedImage): MimeBodyPart {
        fun imageToByteArray(originalImage: BufferedImage): ByteArray {
            ByteArrayOutputStream().use { baos ->
                ImageIO.write(originalImage, "png", baos)
                baos.flush()
                return baos.toByteArray()
            }
        }

        val imageBytes = imageToByteArray(image)
        val bds = ByteArrayDataSource(imageBytes, "image/png")
        return MimeBodyPart().apply {
            dataHandler = DataHandler(bds)
            fileName = "image.png"
            setHeader("Content-ID", "<image>")
        }.also {
            logger().info("Preparing body part for image")
        }
    }
}