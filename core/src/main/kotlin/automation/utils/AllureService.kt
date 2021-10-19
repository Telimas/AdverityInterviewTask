package automation.utils

import com.google.gson.GsonBuilder
import io.qameta.allure.Allure

object AllureService {
    private val printer = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()

    fun attachString(name: String, dumpString: String) {
        Allure.addAttachment(name, "text/plain", dumpString)
    }

    fun attachObjToJson(name: String, dumpObject: Any) {
        Allure.addAttachment(name, "application/json", printer.toJson(dumpObject))
    }
}