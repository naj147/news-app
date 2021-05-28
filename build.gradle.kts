buildscript {
    val kotlin_version by extra("1.4.32")
    repositories {
        google()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.androidGradlePlugin}")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx/")
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
