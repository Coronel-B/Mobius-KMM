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
    jcenter()
}

dependencies {
    testImplementation("junit:junit:4.13.2")

//    Depend on the android gradle plugin, since we want to access it in our plugin
    implementation("com.android.tools.build:gradle:4.1.2")

//    Depend on the kotlin plugin, since we want to access it in our plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")

//    Depend on the default Gradle API's since we want to build a custom plugin
    implementation(gradleApi())
    implementation(localGroovy())
}