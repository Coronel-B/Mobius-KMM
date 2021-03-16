package gradlePlugins.android

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * USING:
 *  . Define a file:
 *      <some-package-id>.properties
 *  . Consume the plugin:
 *      plugins { id("<some-package-id>") }
 * PRECONDITION:
 *  . Pay attention to the package of implementation-class
 *      buildSrc/src/main/kotlin/gradlePlugins/android/AndroidLibraryPlugin.kt
 *  . Define a buildSrc/src/resources/META-INF/gradlePlugins/<some-package-id>.properties file:
 *      implementation-class=gradlePlugins.android.AndroidLibraryPlugin
 * OBS:
 *  . Sample of <some-package-id> could be "com.itdevexpert.mobius.android-library"
 *
 * Source:
 * https://stackoverflow.com/a/65030997/5279996
 */
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configurePlugins()
//        project.configureAndroid()
//        project.configureDependencies()
    }

}
