plugins {
    kotlin("jvm") version "1.9.22"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.9.22"
    application
}

repositories {
    mavenCentral()
}

val springBootVersion = "3.3.3"
val restAssuredVersion = "5.2.0"

dependencies {
    implementation(project(":clean-architecture-application"))
    implementation(project(":clean-architecture-hibernate-adapter"))

    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("commons-logging:commons-logging:1.2")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.platform:junit-platform-suite-api:1.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion") {
        // Conflict into io.rest-assured:rest-assured:5.2.0
        exclude("org.codehaus.groovy")
    }
    implementation("org.postgresql:postgresql:42.5.0")
    testImplementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:xml-path:$restAssuredVersion")
    testImplementation("io.rest-assured:json-schema-validator:$restAssuredVersion")
    testImplementation("org.testcontainers:testcontainers:1.17.5")
    testImplementation("org.testcontainers:junit-jupiter:1.17.5")
    testImplementation("org.testcontainers:postgresql:1.17.5")
    implementation("com.h2database:h2:2.1.214")
}

tasks.test {
    useJUnitPlatform()
}