package automation.pages

import automation.configs.UrlCofig
import automation.enums.WarningCode
import org.jsoup.Jsoup
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO

class MeteorologyPage(sourceHtml: String) : AbstractPage(sourceHtml) {
    val image by lazy { extractImage() }
    val warningCode by lazy { extractWarningCode() }

    private fun extractImage(): BufferedImage {
        val src = Jsoup.parse(sourceHtml).select("img[src*=karta][src$='.png']").first().attr("src")
        return ImageIO.read(URL("${UrlCofig.meteoMdUrl}${src.removePrefix("..")}"))
    }

    private fun extractWarningCode(): WarningCode {
        Jsoup.parse(sourceHtml).select("img[src*=codtexten][src$='.png']").first().attr("src").let {
            with(it) {
                return when {
                    endsWith("1.png") -> WarningCode.GREEN
                    endsWith("2.png") -> WarningCode.YELLOW
                    endsWith("3.png") -> WarningCode.ORANGE
                    endsWith("4.png") -> WarningCode.RED
                    else -> throw RuntimeException("Couldn't parse status code using [src] attribute [$it]")
                }
            }
        }
    }
}