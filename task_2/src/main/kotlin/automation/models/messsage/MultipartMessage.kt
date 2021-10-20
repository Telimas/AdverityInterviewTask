package automation.models.messsage

import automation.utils.logger
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.activation.DataHandler
import javax.imageio.ImageIO
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

class MultipartMessage private constructor(val multipart: MimeMultipart) {
    class Builder {
        private val multipart = MimeMultipart()

        fun withText(text: String) = apply { multipart.addBodyPart(stringToMimeBodyPart(text)) }
        fun withImage(image: BufferedImage) = apply { multipart.addBodyPart(imageToMimeBodyPart(image)) }
        fun build() = MultipartMessage(multipart)

        private fun stringToMimeBodyPart(text: String): MimeBodyPart {
            return MimeBodyPart().also {
                it.setContent(text, "text/html")
                    .also { logger().info("Email text is set to [$text]") }
            }
        }

        private fun imageToMimeBodyPart(image: BufferedImage): MimeBodyPart {
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
}