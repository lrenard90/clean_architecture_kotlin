package fr.renard.clean_architecture_domain.shared.time

import fr.renard.clean_architecture_domain.socle.time.DateProvider
import java.time.LocalDateTime

class FakeDateProvider: DateProvider() {
    lateinit var now: LocalDateTime;

    override fun now(): LocalDateTime {
        return now;
    }
}
