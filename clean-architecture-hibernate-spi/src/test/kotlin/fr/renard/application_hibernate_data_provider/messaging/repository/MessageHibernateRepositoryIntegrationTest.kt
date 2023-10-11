package fr.renard.application_hibernate_data_provider.messaging.repository

import fr.renard.application_hibernate_data_provider.messaging.jpa.repository.MessageHibernateRepository
import fr.renard.application_hibernate_data_provider.messaging.jpa.repository.MessageJpaEntityHibernateRepository
import fr.renard.clean_architecture_domain.messaging.model.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
internal class MessageHibernateRepositoryIntegrationTest @Autowired constructor(val messageHibernateRepository: MessageHibernateRepository) {

    @Autowired
    lateinit var messageJpaEntityHibernateRepository: MessageJpaEntityHibernateRepository

    @Test
    fun `save a message`() {
        val messageToSave = Message(
            UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
            "Alice",
            "Hello world!",
            LocalDateTime.of(2020, 1, 1, 0, 0, 0),
        )

        val savedMessage = messageHibernateRepository.save(messageToSave)

        assertThat(savedMessage.snapshot()).isEqualTo(messageToSave.snapshot())
    }
}