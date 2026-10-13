plugins {
    id("io.freefair.lombok") version "9.8.0" apply false
}

allprojects {
    apply {
        plugin("java")
        plugin("io.freefair.lombok")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "compileOnly"("org.jetbrains:annotations:26.1.0")
    }
}
