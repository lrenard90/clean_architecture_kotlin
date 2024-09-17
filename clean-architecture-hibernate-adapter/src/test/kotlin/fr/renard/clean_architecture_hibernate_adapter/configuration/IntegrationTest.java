package fr.renard.clean_architecture_hibernate_adapter.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "fr.renard.clean_architecture_hibernate_adapter")
public abstract class IntegrationTest {
}
