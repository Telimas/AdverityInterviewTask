package automation.models.messsage

import automation.utils.logger
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.activation.DataHandler
import javax.imageio.ImageIO
import javax.mail.internet.MimeBodyPart
import javax.mail.util.ByteArrayDataSource

class BaseMessageWithImage(text: String, image: BufferedImage) : BaseMessage(text) {
    init {
        multipart.addBodyPart(createMessageBodyPartWithImage(image))
    }

    private fun createMessageBodyPartWithImage(image: BufferedImage): MimeBodyPart {
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