package automation.service

import automation.configs.UrlConfig
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

object MeteoMdService {
    private val request: RequestSpecification
        get() = RestAssured.given().baseUri(UrlConfig.meteorologyUrl)

    fun getHtmlString(): String {
        val response = try {
            request.get()
        } catch (e: Exception) {
            throw RuntimeException("Couldn't get HTML string from meteorological resource", e)
        }
        return if (response.statusCode == 200)
            response.asString()
        else {
            //TODO: Log raw response
            throw RuntimeException("Expected status code [200], but was found [${response.statusCode}]")
        }
    }
}