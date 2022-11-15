package fr.renard.springbootrest.e2e.configuration

import io.restassured.RestAssured
import org.aspectj.lang.annotation.Before
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import javax.annotation.PostConstruct

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2eApiTest() {

    @LocalServerPort
    private var port: Int = 0

    @PostConstruct
    fun postConstruct() {
        RestAssured.port = port
//        postgreSQLContainer.start()
    }

    // FIXME Caused by: java.lang.IllegalStateException: Mapped port can only be obtained after the container is started
//    companion object {
//        val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:13.6-alpine").apply {
//            withDatabaseName("test-database")
//            withUsername("test")
//            withPassword("test")
//        }
//
//        @JvmStatic
//        @DynamicPropertySource
//        fun properties(registry: DynamicPropertyRegistry) {
//            registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//            registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//            registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        }
//    }

}
