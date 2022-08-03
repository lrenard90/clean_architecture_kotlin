package fr.renard.springbootrest.configuration

import fr.renard.clean_architecture_domain.product.usecases.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["fr.renard.clean_architecture_domain"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [UseCase::class])]
)
class CustomAnnotationScanConfiguration {
}