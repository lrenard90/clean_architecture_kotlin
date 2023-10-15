package fr.renard.clean_architecture_application.shared.time

import fr.renard.clean_architecture_application.socle.time.DateProvider
import java.time.LocalDateTime

class FakeDateProvider: DateProvider() {
    lateinit var now: LocalDateTime;

    override fun now(): LocalDateTime {
        return now;
    }
}
