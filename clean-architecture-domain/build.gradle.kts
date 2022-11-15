plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

val cucumberVersion = "7.8.1";

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-java8:$cucumberVersion")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.jar {enabled = true}

tasks.test {
    useJUnitPlatform()
//    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}