/**
 * Plugins and Apply from using DSL: https://stackoverflow.com/a/60482635/5279996
 */
plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation(kotlin("script-runtime"))
}