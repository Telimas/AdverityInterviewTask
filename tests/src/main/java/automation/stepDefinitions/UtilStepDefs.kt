package automation.stepDefinitions

import automation.context.TestContext
import automation.context.TestRunContext
import io.cucumber.java.en.Given
import org.junit.Assume

class UtilStepDefs(private val testContext: TestContext) {
    @Given("^precondition \"(.*)\" is loaded$")
    fun preconditionIsLoaded(preconditionName: String) {
        try {
            /*
            Note that this part was simplified implicitly as a part of "interview task".
            Ideally all fields should be copied from context using copy constructor.
             */
            testContext.photos = TestRunContext.getPrecondition(preconditionName).photos
        } catch (e: Exception) {
            Assume.assumeTrue("Precondition [$preconditionName] was not found", false)
        }
    }
}