@file:Suppress("Annotator")

import org.gradle.api.JavaVersion

object AndroidSettings {

    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 23
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
