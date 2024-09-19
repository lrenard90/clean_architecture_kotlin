package fr.renard.clean_architecture_hibernate_adapter.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EntityScan("fr.renard.clean_architecture_hibernate_adapter")
@ComponentScan(basePackages = ["fr.renard.clean_architecture_hibernate_adapter"])
@EnableJpaRepositories("fr.renard.clean_architecture_hibernate_adapter")
abstract class IntegrationTest: ContainerizedTest()
