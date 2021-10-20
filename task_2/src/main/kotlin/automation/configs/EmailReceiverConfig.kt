package automation.configs

import automation.delegates.SystemPropertyDelegate

object EmailReceiverConfig {
    val to: String by SystemPropertyDelegate("to")
}