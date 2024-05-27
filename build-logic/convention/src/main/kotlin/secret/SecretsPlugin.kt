package secret

import com.android.build.api.variant.Variant
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.FileNotFoundException
import java.util.Properties

/**
 * Plugin that reads secrets from a properties file and injects manifest build and BuildConfig
 * variables into an Android project. Since property keys are turned into Java variables,
 * invalid variable characters from the property key are removed.
 *
 * e.g.
 * A key defined as "sdk.dir" in the properties file will be converted to "sdkdir".
 */
class SecretsPlugin : Plugin<Project> {

    private val extensionName = "secrets"

    override fun apply(project: Project) {
        val extension = project.extensions.create(
            extensionName,
            SecretsPluginExtension::class.java
        )
        val supportedComponents =
            listOf(project.androidAppComponent(), project.androidLibraryComponent())
        supportedComponents.forEach { component ->
            component?.onVariants { variant ->
                val defaultProperties = extension.defaultPropertiesFileName?.let {
                    project.loadPropertiesFile(it)
                }

                val properties: Properties? = try {
                    project.loadPropertiesFile(
                        extension.propertiesFileName
                    )
                } catch (e: FileNotFoundException) {
                    defaultProperties ?: throw e
                }
                generateConfigKey(project, extension, defaultProperties, properties, variant)
            }
        }
    }

    private fun generateConfigKey(
        project: Project,
        extension: SecretsPluginExtension,
        defaultProperties: Properties?,
        properties: Properties?,
        variant: Variant
    ) {
        // Inject defaults first
        defaultProperties?.let {
            variant.inject(it, extension.ignoreList)
        }

        properties?.let {
            variant.inject(properties, extension.ignoreList)
        }

        // Inject build-type specific properties
        val buildTypeFileName = "secrets.${variant.buildType}.properties"
        val buildTypeProperties = try {
            project.loadPropertiesFile(buildTypeFileName)
        } catch (e: FileNotFoundException) {
            null
        }
        buildTypeProperties?.let {
            variant.inject(it, extension.ignoreList)
        }

        // Inject flavor-specific properties
        val flavorFileName = "secrets.${variant.flavorName}.properties"
        val flavorProperties = try {
            project.loadPropertiesFile(flavorFileName)
        } catch (e: FileNotFoundException) {
            null
        }
        flavorProperties?.let {
            variant.inject(it, extension.ignoreList)
        }
    }
}