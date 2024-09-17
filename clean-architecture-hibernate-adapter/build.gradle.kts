plugins {
    kotlin("jvm")
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    kotlin("kapt")
}

val springBootVersion = "3.3.3"
val mapstructVersion = "1.5.3.Final"

repositories {
    mavenCentral()
}

kapt {
    dependencies {
        kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    }
}

dependencies {
    implementation(project(":clean-architecture-application"))
    testImplementation(testFixtures(project(":clean-architecture-application")))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation("org.liquibase:liquibase-core")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    testImplementation("com.h2database:h2:2.1.214")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}