package automation.cucumber

import automation.context.TestContext
import automation.context.TestRunContext
import io.cucumber.java.After
import io.cucumber.java.Scenario
import io.cucumber.java.Status

class CucumberScenarioHooks(private val testContext: TestContext) {
    @After("@Precondition", order = 100)
    fun handlePrecondition(scenario: Scenario) {
        if (scenario.status == Status.PASSED)
            TestRunContext.addPrecondition(
                scenario.name.substringAfter("{").substringBefore("}"),
                testContext
            )
    }
}