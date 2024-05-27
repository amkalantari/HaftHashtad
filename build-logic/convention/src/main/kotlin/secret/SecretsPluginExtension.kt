package secret

/**
 * Configuration object for [SecretsPlugin].
 */
open class SecretsPluginExtension {
    /**
     * The name of the properties file containing secrets. Defaults to "$defaultPropertiesFile"
     */
    var propertiesFileName: String = defaultPropertiesFile

    /**
     * A list of keys this plugin should ignore and not inject. Defaults to $defaultIgnoreList
     */
    var ignoreList: MutableList<String> = defaultIgnoreList

    /**
     * The name of the properties file containing secrets' default values.
     */
    var defaultPropertiesFileName: String? = null

    companion object {
        const val defaultPropertiesFile = "local.properties"
        val defaultIgnoreList = mutableListOf("sdk.dir")
    }
}