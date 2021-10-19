package automation.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Any> T.logger(): Logger {
    return logger(this.javaClass)
}

private fun <T : Any> logger(forClass: Class<T>): Logger {
    return LoggerFactory.getLogger(forClass)
}