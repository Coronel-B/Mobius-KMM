//TODO: Check plugins
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("com.google.code.gson:gson:${V.gson}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${V.kotlin}")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}