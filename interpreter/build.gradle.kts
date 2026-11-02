plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ast"))
    implementation(project(":parser"))
}
