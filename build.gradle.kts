plugins {
    kotlin("jvm") version "2.3.10"
}

group = "nz.sounie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(platform("org.http4k:http4k-bom:6.43.0.0"))

    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-client-okhttp")

    implementation("org.jsoup:jsoup:1.22.1")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}