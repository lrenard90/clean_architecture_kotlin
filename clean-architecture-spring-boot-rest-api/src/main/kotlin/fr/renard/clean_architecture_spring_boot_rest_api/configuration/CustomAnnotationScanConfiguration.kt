package fr.renard.clean_architecture_spring_boot_rest_api.configuration

import fr.renard.clean_architecture_application.socle.dependency_injection.annotation.Mapper
import fr.renard.clean_architecture_application.socle.dependency_injection.annotation.UseCase
import fr.renard.clean_architecture_application.socle.time.CurrentDateProvider
import fr.renard.clean_architecture_application.socle.time.DateProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan(
    basePackages = ["fr.renard.clean_architecture_application", "fr.renard.clean_architecture_hibernate_adapter"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [UseCase::class, Mapper::class])],
)
@EnableJpaRepositories(basePackages = ["fr.renard.clean_architecture_hibernate_adapter"])
class CustomAnnotationScanConfiguration {

    @Bean
    fun dateProvider(): DateProvider {
        return CurrentDateProvider()
    }

}