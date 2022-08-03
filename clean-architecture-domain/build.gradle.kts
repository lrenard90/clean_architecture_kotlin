plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.cucumber:cucumber-java:7.3.2")
    testImplementation("io.cucumber:cucumber-junit:7.3.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    implementation("io.cucumber:cucumber-java8:7.3.2")
}

tasks.jar {enabled = true}

tasks.test {
    useJUnitPlatform()
//    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}