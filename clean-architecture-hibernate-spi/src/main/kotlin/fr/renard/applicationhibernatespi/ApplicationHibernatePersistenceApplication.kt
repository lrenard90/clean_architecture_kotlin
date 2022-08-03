package fr.renard.applicationhibernatespi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationHibernatePersistenceApplication

fun main(args: Array<String>) {
    runApplication<ApplicationHibernatePersistenceApplication>(*args)
}
