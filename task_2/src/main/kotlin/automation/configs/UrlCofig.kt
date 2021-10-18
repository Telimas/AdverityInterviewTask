package automation.configs

import automation.utils.ResourceAccessor
import automation.utils.getNonNullProperty

object UrlCofig {
    val meteoMdUrl: String
    val meteorologyUrl: String

    init {
        val props = ResourceAccessor().loadPropsFrom("url.properties")
        meteoMdUrl = props.getNonNullProperty("meteomd.url")
        meteorologyUrl = props.getNonNullProperty("meteomd.state.hydrometeorological.service.url")
    }
}