plugins {
    kotlin("jvm")
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
    application
}

repositories {
    mavenCentral()
}

val cucumberVersion = "7.8.1";
val restAssuredVersion = "5.2.0";

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":clean-architecture-domain"))
    implementation(project(":clean-architecture-hibernate-spi"))
    implementation("commons-logging:commons-logging:1.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-java8:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-spring:7.9.0")
    testImplementation("org.testcontainers:testcontainers:1.17.5")
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
    testImplementation("org.testcontainers:junit-jupiter:1.17.5")
    testImplementation("org.testcontainers:postgresql:1.17.5")
    implementation("com.h2database:h2:2.1.214")
}

tasks.test {
    useJUnitPlatform()
}