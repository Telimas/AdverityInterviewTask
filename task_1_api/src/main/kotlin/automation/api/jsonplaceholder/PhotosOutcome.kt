package automation.api.jsonplaceholder

import automation.models.Photo

/**
 * Sealed class represents all possible /photos endpoint outcomes to provide more control over each state.
 */
sealed class PhotosOutcome {
    data class Success(val photos: List<Photo>) : PhotosOutcome()
    data class Failure(val statusCode: Int, val response: String) : PhotosOutcome()
    data class Error(val ex: Exception, val response: String?) : PhotosOutcome()
}
