package fr.renard.clean_architecture_hibernate_adapter.messaging.repository

import fr.renard.clean_architecture_application.messaging.builders.MessageBuilder
import fr.renard.clean_architecture_application.messaging.domain.entity.Message
import fr.renard.clean_architecture_application.messaging.domain.entity.MessageData
import fr.renard.clean_architecture_hibernate_adapter.configuration.IntegrationTest
import fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.entity.MessageJpaEntity
import fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.repository.MessageHibernateRepository
import fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.repository.MessageJpaEntityHibernateRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*

internal class MessageHibernateRepositoryIntegrationTest @Autowired constructor(val messageHibernateRepository: MessageHibernateRepository) :
    IntegrationTest() {

    @Autowired
    lateinit var messageJpaEntityHibernateRepository: MessageJpaEntityHibernateRepository

    @Nested
    inner class SaveMessage {
        @Test
        fun `create a message`() {
            val messageToSave = MessageBuilder()
                .withId(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .withAuthor("Alice")
                .withText("Hello world!")
                .withPublishedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build()

            messageHibernateRepository.save(messageToSave)

            val messageJpaEntity: MessageJpaEntity = messageJpaEntityHibernateRepository.getReferenceById(messageToSave.id)
            val messageData: MessageData = messageToSave.data()
            assertThat(messageJpaEntity.id).isEqualTo(messageData.id)
            assertThat(messageJpaEntity.author).isEqualTo(messageData.author)
            assertThat(messageJpaEntity.text).isEqualTo(messageData.text)
            assertThat(messageJpaEntity.publishedDate).isEqualTo(messageData.publishedDate)
        }

        @Test
        fun `update a message text`() {
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
                    )
                )
            )

            val messageToSave = MessageBuilder()
                .withId(UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .withAuthor("Alice")
                .withText("Hello world! updated")
                .withPublishedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build()

            messageHibernateRepository.save(messageToSave)

            val messageJpaEntity: MessageJpaEntity = messageJpaEntityHibernateRepository.getReferenceById(messageToSave.id)
            val messageData: MessageData = messageToSave.data()
            assertThat(messageJpaEntity.id).isEqualTo(messageData.id)
            assertThat(messageJpaEntity.author).isEqualTo(messageData.author)
            assertThat(messageJpaEntity.text).isEqualTo(messageData.text)
            assertThat(messageJpaEntity.publishedDate).isEqualTo(messageData.publishedDate)
        }
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

        assertThat(messages.map { it.data() }).containsExactly(
            MessageData(
                UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                "Alice",
                "Message A",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0)
            ),
            MessageData(
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

            assertThat(message.get().data()).isEqualTo(
                MessageData(
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