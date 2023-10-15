package fr.renard.clean_architecture_hibernate_adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationHibernatePersistenceApplication

fun main(args: Array<String>) {
    runApplication<ApplicationHibernatePersistenceApplication>(*args)
}
