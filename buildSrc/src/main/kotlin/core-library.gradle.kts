/**
 * https://stackoverflow.com/a/65030997/5279996
 */
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}