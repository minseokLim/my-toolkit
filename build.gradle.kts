plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.minseoklim"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.18.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
