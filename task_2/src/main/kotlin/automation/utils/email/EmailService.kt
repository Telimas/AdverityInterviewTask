package automation.utils.email

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
    private val from: String = System.getenv("INTERVIEW_USER")
        ?: throw RuntimeException("Couldn't get environment variable [INTERVIEW_USER]")

    private val password: String = System.getenv("INTERVIEW_PASS")
        ?: throw RuntimeException("Couldn't get environment variable [INTERVIEW_PASS]")

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

    fun sendEmailTo(to: String, subject: String, text: String, image: BufferedImage) {
        try {
            val message = MimeMessage(session).also {
                it.setFrom(from)
                it.addRecipient(Message.RecipientType.TO, InternetAddress(to))
                it.subject = subject
            }

            val multipart = MimeMultipart()

            val bodyPart = MimeBodyPart().also {
                it.setContent(text, "text/html")
            }
            val imagePart = prepareMessageBodyPartWithImage(image)

            multipart.addBodyPart(bodyPart)
            multipart.addBodyPart(imagePart)

            message.setContent(multipart)

            Transport.send(message)
        } catch (e: MessagingException) {
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
        }
    }
}