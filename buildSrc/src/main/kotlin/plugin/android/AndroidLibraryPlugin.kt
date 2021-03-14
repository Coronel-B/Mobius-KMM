package plugin.android

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * PRECONDITION: Define in <some-package>.properties file following:
 *      implementation-class=com.itdevexpert.mobius.androidApp.AndroidPlugin
 * USING:
 *      plugins { id("com.itdevexpert.mobius.androidApp") }
 *
 * Source:
 * https://stackoverflow.com/a/65030997/5279996
 * https://youtrack.jetbrains.com/issue/KT-26271
 */
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configureAndroid()
        project.configureDependencies()
        project.configurePlugins()
    }

}
