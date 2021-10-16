package automation.context

import java.util.*

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