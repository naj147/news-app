plugins {
    kotlin("jvm")
}
dependencies {
    implementation(project(":common"))
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.koinCore)
    implementation(Dependencies.kotlinStdLib)

    testImplementation(TestDependencies.assertJ)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
}

java {
    sourceCompatibility = AndroidSettings.sourceCompatibility
    targetCompatibility = AndroidSettings.targetCompatibility
}
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

