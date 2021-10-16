package automation.api.jsonplaceholder

import automation.models.Photo

sealed class PhotosOutcome {
    data class Success(val photos: List<Photo>) : PhotosOutcome()
    data class Failure(val statusCode: Int, val response: String) : PhotosOutcome()
    data class Error(val ex: Exception, val response: String?) : PhotosOutcome()
}
