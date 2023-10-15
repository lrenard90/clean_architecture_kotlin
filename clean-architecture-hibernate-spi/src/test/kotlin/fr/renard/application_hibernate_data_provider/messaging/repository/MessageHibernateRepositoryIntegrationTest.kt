package fr.renard.application_hibernate_data_provider.messaging.repository

import fr.renard.application_hibernate_data_provider.messaging.jpa.entity.MessageJpaEntity
import fr.renard.application_hibernate_data_provider.messaging.jpa.repository.MessageHibernateRepository
import fr.renard.application_hibernate_data_provider.messaging.jpa.repository.MessageJpaEntityHibernateRepository
import fr.renard.clean_architecture_domain.messaging.builders.MessageBuilder
import fr.renard.clean_architecture_domain.messaging.model.entity.Message
import fr.renard.clean_architecture_domain.messaging.model.entity.MessageState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
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
        val messageToSave = MessageBuilder()
            .withId(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
            .withAuthor("Alice")
            .withText("Hello world!")
            .withPublishedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
            .build()

        messageHibernateRepository.save(messageToSave)

        val messageJpaEntity: MessageJpaEntity = messageJpaEntityHibernateRepository.getById(messageToSave.id)
        val messageState: MessageState = messageToSave.snapshot()
        assertThat(messageJpaEntity.id).isEqualTo(messageState.id)
        assertThat(messageJpaEntity.author).isEqualTo(messageState.author)
        assertThat(messageJpaEntity.text).isEqualTo(messageState.text)
        assertThat(messageJpaEntity.publishedDate).isEqualTo(messageState.publishedDate)
    }

    @Test
    fun `find all by author`() {
        messageJpaEntityHibernateRepository.saveAll(
            listOf(
                MessageJpaEntity(
                    UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                    "Alice",
                    "Message A",
                    LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                ),
                MessageJpaEntity(
                    UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"),
                    "Alice",
                    "Message B",
                    LocalDateTime.of(2021, 2, 1, 0, 0, 0)
                ),
                MessageJpaEntity(
                    UUID.fromString("c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13"),
                    "Bob",
                    "Message C",
                    LocalDateTime.of(2022, 3, 1, 0, 0, 0)
                )
            )
        )

        val messages: List<Message> = messageHibernateRepository.findAllByAuthor("Alice")

        assertThat(messages.map { it.snapshot() }).containsExactly(
            MessageState(
                UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                "Alice",
                "Message A",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0)
            ),
            MessageState(
                UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"),
                "Alice",
                "Message B",
                LocalDateTime.of(2021, 2, 1, 0, 0, 0)
            )
        )
    }

    @Nested
    inner class ExistsById {
        @Test
        fun testExistsById_Exists() {
            messageJpaEntityHibernateRepository.saveAll(
                listOf(
                    MessageJpaEntity(
                        UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                        "Alice",
                        "Message A",
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    )
                )
            )

            val existsById = messageHibernateRepository.existsById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))

            assertThat(existsById).isTrue()
        }

        @Test
        fun testExistsById_notExists() {
            messageJpaEntityHibernateRepository.saveAll(
                listOf(
                    MessageJpaEntity(
                        UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                        "Alice",
                        "Message A",
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    )
                )
            )

            val existsById = messageHibernateRepository.existsById(UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))

            assertThat(existsById).isFalse()
        }
    }

    @Nested
    inner class FindById {
        @Test
        fun testFindById_Exists() {
            messageJpaEntityHibernateRepository.saveAll(
                listOf(
                    MessageJpaEntity(
                        UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                        "Alice",
                        "Message A",
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    )
                )
            )

            val message = messageHibernateRepository.findById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))

            assertThat(message.get().snapshot()).isEqualTo(
                MessageState(
                    UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                    "Alice",
                    "Message A",
                    LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                )
            )
        }

        @Test
        fun testFindById_notExists() {
            messageJpaEntityHibernateRepository.saveAll(
                listOf(
                    MessageJpaEntity(
                        UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                        "Alice",
                        "Message A",
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    )
                )
            )

            val message = messageHibernateRepository.findById(UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))

            assertThat(message).isNotPresent
        }
    }
}