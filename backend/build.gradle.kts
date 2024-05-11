plugins {
    kotlin("jvm") version "1.9.23"
}

group = "br.com.imeavalia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
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