package automation.context

import java.util.*

/**
 * Holds all submitted contexts during JVM run.
 * Helps with moving context data between scenarios.
 */
object TestRunContext {
    private var scenarioContextMap: MutableMap<String, TestContext> = Collections.synchronizedMap(mutableMapOf())

    fun getPrecondition(preconditionName: String): TestContext {
        return scenarioContextMap[preconditionName]
            ?: throw RuntimeException("Couldn't get context for [$preconditionName] pre-condition name")
    }

    fun addPrecondition(preconditionName: String, testContext: TestContext) {
        scenarioContextMap[preconditionName] = testContext
    }
}