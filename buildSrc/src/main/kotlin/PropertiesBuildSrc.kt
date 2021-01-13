import java.io.FileInputStream
import java.util.*

object PropertiesBuildSrc {

    const val GRADLE_PROPERTIES = "buildSrc/gradle.properties"
    const val SAMPLE_PROP = "valueSampleProp"

    fun getSampleProp(): String? {
        Properties().apply { load(FileInputStream(GRADLE_PROPERTIES)) }.let {
            return it.getProperty(SAMPLE_PROP)
        }
    }
}