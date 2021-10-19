package automation.models.messsage

import javax.mail.internet.MimeMultipart

abstract class AbstractMessage {
    val multipart = MimeMultipart()
}