// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        /**
         * WARNING: Exists a bug in AGP 7.0 for KMM
         * Versions:
         *      https://developer.android.com/studio/releases/gradle-plugin
         *      https://mvnrepository.com/artifact/com.android.tools.build/gradle?repo=google
         */
//        classpath "com.android.tools.build:gradle:4.2.0-beta06" // With Java 8
        classpath("com.android.tools.build:gradle:7.0.0-alpha11") // With Java 8

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

        /**
         * kotlinx-datatime
         * TODO: soon will be just jcenter()
         * https://stackoverflow.com/a/65316797/5279996
         */
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }
}