import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    `java-test-fixtures`
}

group = "uk.co.alistaironeill"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.strikt:strikt-core:0.33.0")
    testFixturesImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testFixturesImplementation("io.strikt:strikt-core:0.33.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}