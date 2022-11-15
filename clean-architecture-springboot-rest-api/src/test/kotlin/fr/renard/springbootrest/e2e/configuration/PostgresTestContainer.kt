package fr.renard.springbootrest.e2e.configuration

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.testcontainers.containers.PostgreSQLContainer

@Component
class PostgresTestContainer: PostgreSQLContainer<PostgresTestContainer?>(
    IMAGE_VERSION
) {

    companion object {
        private const val IMAGE_VERSION = "postgres:11.1"
        private var container: PostgresTestContainer? = null
        val instance: PostgresTestContainer?
            get() {
                if (container == null) {
                    container = PostgresTestContainer()
                }
                return container
            }
    }

    override fun start() {
        super.start()
        System.setProperty("DB_URL", container!!.jdbcUrl)
        System.setProperty("DB_USERNAME", container!!.username)
        System.setProperty("DB_PASSWORD", container!!.password)
    }

    override fun stop() {
        //do nothing, JVM handles shut down
    }
}
