import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id('kotlin')
    kotlin("jvm") version "1.9.22"

    // A Java Library
    `java-library`
    // which produces test fixtures
    `java-test-fixtures`
    // and is published
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.9.1")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.jar {enabled = true}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

