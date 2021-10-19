package automation.service

import automation.configs.UrlConfig
import automation.utils.logger
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

object MeteoMdService {
    private val request: RequestSpecification
        get() = RestAssured.given().baseUri(UrlConfig.meteorologyUrl)

    fun getHtmlString(): String {
        logger().info("Getting HTML string from Meteo.md service")
        val response = try {
            request.get()
        } catch (e: Exception) {
            logger().error("Couldn't get html string response")
            throw RuntimeException("Couldn't get HTML string from meteorological resource", e)
        }
        return if (response.statusCode == 200) {
            logger().info("Received HTML string with success")
            response.asString()
        } else {
            logger().warn("Received HTML string with failure")
            throw RuntimeException("Expected status code [200], but was found [${response.statusCode}]")
        }
    }
}