package automation.api.jsonplaceholder

import automation.api.jsonplaceholder.responses.PhotosResponse
import automation.utils.logger
import com.google.gson.Gson
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

object JsonPlaceholderApi {
    private val request: RequestSpecification
        get() = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com")

    fun getPhotos(): PhotosOutcome {
        logger().info("Getting photos")
        val response = try {
            request.get("photos")
        } catch (e: Exception) {
            logger().error("Couldn't get photos response")
            return PhotosOutcome.Error(e, null)
        }
        return try {
            val parsedResponse = Gson().fromJson(response.asString(), PhotosResponse::class.java)
            if (response.statusCode == 200) {
                logger().info("Received photos with success")
                PhotosOutcome.Success(parsedResponse)
            } else {
                logger().warn("Received photos with failure")
                PhotosOutcome.Failure(response.statusCode, response.asString())
            }
        } catch (e: Exception) {
            logger().error("Couldn't parse photos response")
            PhotosOutcome.Error(e, response.asString())
        }
    }
}