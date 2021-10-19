import org.junit.internal.RealSystem
import org.junit.internal.TextListener
import org.junit.runner.JUnitCore
import org.junit.runner.Request
import kotlin.system.exitProcess

/**
 * Main method was created for jar/maven run.
 * You can ignore this method and run tests directly from:
 * @see RunCucumberTests
 * @see HydroMeteorologicChecker
 */
fun main(args: Array<String>) {
    /*
    Checking for [task2] system property just not to start 2 separate test classes and the same time.
     */
    if (System.getProperty("task2").isNullOrEmpty())
    /*
    Starting Cucumber tests.
     */
        JUnitCore.main(RunCucumberTests::class.java.canonicalName, *args)
    else {
        /*
        Starting Service automation.
         */
        val req = Request.method(HydroMeteorologicChecker::class.java, "checkWarnings")
        val res = JUnitCore().also { it.addListener(TextListener(RealSystem())) }.run(req)
        exitProcess(if (res.wasSuccessful()) 0 else 1)
    }
}