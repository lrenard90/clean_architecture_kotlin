package fr.renard.clean_architecture_domain.socle.time

import java.time.LocalDateTime

abstract class DateProvider {

    abstract fun now(): LocalDateTime

}
