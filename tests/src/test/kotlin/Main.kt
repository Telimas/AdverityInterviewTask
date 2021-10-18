import org.junit.runner.JUnitCore
import org.junit.runner.Request
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (System.getProperty("task2").isNullOrEmpty())
        JUnitCore.main(RunCucumberTests::class.java.canonicalName, *args)
    else {
        val req = Request.method(HydroMeteorologicChecker::class.java, "checkWarnings")
        val res = JUnitCore().run(req)
        exitProcess(if (res.wasSuccessful()) 0 else 1)
    }
}