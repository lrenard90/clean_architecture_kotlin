package fr.renard.clean_architecture_spring_boot_rest_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootRestApplication

fun main(args: Array<String>) {
    runApplication<SpringbootRestApplication>(*args)
}
