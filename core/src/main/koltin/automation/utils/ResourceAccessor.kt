package automation.utils

import java.io.FileInputStream
import java.io.InputStream
import java.util.*

class ResourceAccessor {
    fun loadPropsFrom(filename: String): Properties {
        logger().info("Loading properties from [$filename] file")
        return Properties().apply { load(getInputStreamFromResource(filename)) }
    }

    private fun getInputStreamFromResource(filename: String): InputStream {
        val inputStream = ResourceAccessor::class.java.getResourceAsStream("/$filename")
        return inputStream ?: FileInputStream(filename)
    }
}

fun Properties.getNonNullProperty(propertyName: String): String {
    return this.getProperty(propertyName)
        ?: throw RuntimeException("[$propertyName] is not specified in the properties file")
}