package fr.renard.clean_architecture_application.socle.time

import java.time.LocalDateTime

abstract class DateProvider {

    abstract fun now(): LocalDateTime

}
