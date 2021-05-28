import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(AndroidSettings.compileSdkVersion)
    buildToolsVersion = "30.0.2"
    defaultConfig {
        applicationId = "com.abel.qvik"
        minSdkVersion(AndroidSettings.minSdkVersion)
        targetSdkVersion(AndroidSettings.targetSdkVersion)
        testInstrumentationRunner = AndroidSettings.testInstrumentationRunner
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = AndroidSettings.sourceCompatibility
        targetCompatibility = AndroidSettings.targetCompatibility
    }
    kotlinOptions {
        this as KotlinJvmOptions
        jvmTarget = "1.8"
    }
    packagingOptions {
        pickFirst("META-INF/services/javax.annotation.processing.Processor")
        exclude("META-INF/main.kotlin_module")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
}
