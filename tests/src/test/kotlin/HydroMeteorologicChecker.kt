import automation.configs.UrlConfig
import automation.enums.WarningCode
import automation.models.messsage.BaseMessageWithImage
import automation.pages.MeteorologyPage
import automation.service.MeteoMdService
import automation.utils.email.EmailService
import org.junit.Assert
import org.junit.Test

class HydroMeteorologicChecker {
    @Test
    fun checkWarnings() {
        val html = MeteoMdService.getHtmlString()
        val meteorologyPage = MeteorologyPage(html)
        when (meteorologyPage.warningCode) {
            WarningCode.GREEN -> Assert.assertTrue(true)
            WarningCode.YELLOW, WarningCode.ORANGE, WarningCode.RED -> {
                val message = BaseMessageWithImage(
                    text = "Check possible risks.\n More info: ${UrlConfig.meteorologyUrl}",
                    image = meteorologyPage.image
                )
                EmailService.sendEmailTo(
                    subject = "Warning! Current meteorology warning code is [${meteorologyPage.warningCode.name}]",
                    messageWithImage = message
                )
                Assert.fail("WARNING. CURRENT METEOROLOGY WARNING CODE IS [${meteorologyPage.warningCode.name}]")
            }
        }
    }
}