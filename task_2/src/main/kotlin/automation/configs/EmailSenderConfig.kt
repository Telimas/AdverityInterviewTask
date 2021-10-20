package automation.configs

import automation.delegates.SystemPropertyDelegate

object EmailSenderConfig {
    val user: String by SystemPropertyDelegate("user")
    val pass: String by SystemPropertyDelegate("pass")
}