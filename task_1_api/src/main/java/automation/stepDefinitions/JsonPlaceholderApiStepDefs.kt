package automation.stepDefinitions

import automation.api.jsonplaceholder.JsonPlaceholderApi
import automation.api.jsonplaceholder.PhotosOutcome
import automation.context.TestContext
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert

class JsonPlaceholderApiStepDefs(private val testContext: TestContext) {
    @When("^user gets photos$")
    fun userGetsPhotos() {
        when (val result = JsonPlaceholderApi.getPhotos()) {
            is PhotosOutcome.Success -> testContext.photos = result.photos
            is PhotosOutcome.Failure -> Assert.fail("Failed to get photos. Returned code is [${result.statusCode}]")
            is PhotosOutcome.Error -> throw RuntimeException("Error after getting photos", result.ex)
        }
    }

    @Then("^number of photos is greater than \"(\\d+)\"$")
    fun numberOfPhotosIsGreaterThanNumber(n: Int) {
        if (n < 0) throw RuntimeException("Unsupported number inz step definition. Expected [n > 0], but was found [$n]")
        Assert.assertTrue("Number of photos is less than [$n]", testContext.photos.size > n)
    }

    @Then("^get photo with \"(.*)\" title$")
    fun getPhotoWithTitle(title: String) {
        testContext.photos.firstOrNull { it.title == title } ?: Assert.fail("Couldn't get photo with [$title] title")
    }

    @Then("^remove all photos that have \"albumId\" different than 100$")
    fun removeAllPhotosThatHaveAlbumIdDifferentThan100() {
        testContext.photos.filter { it.albumId == 100 }.let {
            if (it.isEmpty()) Assert.fail("There are no photos where albumId equals to 100")
            testContext.photos = it
        }
    }

    @Then("^remove all photos that do not contain the word \"error\" in title$")
    fun removeAllPhotosThatDoNotContainTheWordErrorInTitle() {
        testContext.photos.filterNot { it.title.contains("error") }.let {
            if (it.isEmpty()) Assert.fail("There are no photos with error word in title")
            testContext.photos = it
        }
    }
}