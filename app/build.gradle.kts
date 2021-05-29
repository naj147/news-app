import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android.extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(AndroidSettings.compileSdkVersion)
    buildToolsVersion = AndroidSettings.buildToolsVersion
    defaultConfig {
        applicationId = "com.abel.qvik"
        minSdkVersion(AndroidSettings.minSdkVersion)
        targetSdkVersion(AndroidSettings.targetSdkVersion)
        testInstrumentationRunner = AndroidSettings.testInstrumentationRunner
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        viewBinding = true
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
        jvmTarget = "1.8"
    }

    androidExtensions {
        isExperimental = true
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
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":remote"))
    implementation(project(":cache"))
    implementation(Dependencies.appCompat)
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.glide)
    implementation(Dependencies.navGraphFragment)
    implementation(Dependencies.navGraphUI)
    implementation(Dependencies.glideOkHttp)
    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.epoxy)
    implementation(Dependencies.lottieAnimation)
    kapt(Dependencies.glideCompiler)
    kapt(Dependencies.epoxyProcessor)

    //test dep
    androidTestImplementation(TestDependencies.assertJAndroid)
    androidTestImplementation(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.espressoCont)
    androidTestImplementation(TestDependencies.koinTest)
    androidTestImplementation(TestDependencies.mockkAndroid) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-reflect")
    }
    androidTestImplementation(TestDependencies.testRule)
    androidTestImplementation(TestDependencies.testRunner)

}
