package fr.renard.springbootrest.configuration

import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.Mapper
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan(
    basePackages = ["fr.renard.clean_architecture_domain", "fr.renard.application_hibernate_data_provider"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [UseCase::class, Mapper::class])],
)
@EnableJpaRepositories(basePackages = ["fr.renard.application_hibernate_data_provider"])
class CustomAnnotationScanConfiguration {
}