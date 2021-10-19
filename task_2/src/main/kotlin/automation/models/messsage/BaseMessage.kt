package automation.models.messsage

import automation.utils.logger
import javax.mail.internet.MimeBodyPart

open class BaseMessage(text: String) : AbstractMessage() {
    init {
        multipart.addBodyPart(createMessageBodyPartWithText(text))
    }

    private fun createMessageBodyPartWithText(text: String): MimeBodyPart {
        return MimeBodyPart().also {
            it.setContent(text, "text/html")
                .also { logger().info("Email text is set to [$text]") }
        }.also {
            logger().info("Preparing body part for email")
        }
    }
}