package fr.renard.application_hibernate_data_provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationHibernatePersistenceApplication

fun main(args: Array<String>) {
    runApplication<ApplicationHibernatePersistenceApplication>(*args)
}
