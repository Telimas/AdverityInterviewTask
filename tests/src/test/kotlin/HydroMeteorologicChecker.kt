import automation.enums.WarningCode
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
                EmailService.sendEmailTo(
                    to = "",
                    subject = "Warning! Current meteorology warning code is [${meteorologyPage.warningCode.name}]",
                    text = "Check possible risks.\n More info: http://old.meteo.md/newen/avcodhiden.htm",
                    image = meteorologyPage.image
                )
                Assert.fail("WARNING. CURRENT METEOROLOGY WARNING CODE IS [${meteorologyPage.warningCode.name}]")
            }
        }
    }
}