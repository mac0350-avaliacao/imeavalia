plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.11"
}

group = "br.com.imeavalia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core:2.3.11")
    implementation("io.ktor:ktor-serialization-gson:2.3.11")
    implementation("io.ktor:ktor-server-netty:2.3.11")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-serialization:2.3.11")
    implementation("io.ktor:ktor-server-cors:2.3.11")
    testImplementation("io.ktor:ktor-server-tests:2.3.11")

    // Exposed framework
    implementation("org.jetbrains.exposed:exposed-core:0.35.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.35.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.35.3")

    // PostgresSQL JDBC Driver
    implementation("org.postgresql:postgresql:42.3.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}