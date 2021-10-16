package automation.api.jsonplaceholder

import automation.models.Photos
import com.google.gson.Gson
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

object JsonPlaceholderApi {
    private val request: RequestSpecification
        get() = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com")

    fun getPhotos(): PhotosOutcome {
        val response = try {
            request.get("photos")
        } catch (e: Exception) {
            return PhotosOutcome.Error(e, null)
        }
        return try {
            val parsedResponse = Gson().fromJson(response.asString(), Photos::class.java)
            if (response.statusCode == 200)
                PhotosOutcome.Success(parsedResponse)
            else
                PhotosOutcome.Failure(response.statusCode, response.asString())
        } catch (e: Exception) {
            PhotosOutcome.Error(e, response.asString())
        }
    }
}