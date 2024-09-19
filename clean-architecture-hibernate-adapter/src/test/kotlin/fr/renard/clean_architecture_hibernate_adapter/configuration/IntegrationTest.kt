package fr.renard.clean_architecture_hibernate_adapter.configuration;

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@ComponentScan(basePackages = ["fr.renard.clean_architecture_hibernate_adapter"])
abstract class IntegrationTest
