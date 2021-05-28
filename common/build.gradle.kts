plugins {
    kotlin("jvm")
}
java {
    sourceCompatibility = AndroidSettings.sourceCompatibility
    targetCompatibility = AndroidSettings.targetCompatibility
}

dependencies {
    implementation(Dependencies.kotlinStdLib)

}
