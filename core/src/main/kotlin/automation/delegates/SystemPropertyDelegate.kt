package automation.delegates

import kotlin.reflect.KProperty

class SystemPropertyDelegate(private val propertyName: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return System.getProperty(propertyName)
            ?: throw RuntimeException("Couldn't get value from [$propertyName] system property. Please add string [-D$propertyName=\"...\"] in VM arguments")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {}
}