package automation.delegates

import kotlin.reflect.KProperty

class EnvironmentVariableDelegate(private val variableName: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return System.getenv(variableName)
            ?: throw RuntimeException("Couldn't get environment variable [$variableName]")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {}
}