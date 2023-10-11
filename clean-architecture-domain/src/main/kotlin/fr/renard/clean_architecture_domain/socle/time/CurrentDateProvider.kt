package fr.renard.clean_architecture_domain.socle.time

import java.time.LocalDateTime

class CurrentDateProvider: DateProvider() {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}