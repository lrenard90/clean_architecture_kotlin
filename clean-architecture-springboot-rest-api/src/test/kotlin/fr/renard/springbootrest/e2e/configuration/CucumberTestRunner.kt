package fr.renard.springbootrest.e2e.configuration

import io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME
import io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME
import io.cucumber.spring.CucumberContextConfiguration
import io.restassured.RestAssured
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.server.LocalServerPort
import javax.annotation.PostConstruct


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fr/renard")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "fr/renard/springbootrest/e2e")
@CucumberContextConfiguration
class CucumberTestRunner : E2eApiTest() {

    private val logger: Logger = LoggerFactory.getLogger(CucumberTestRunner::class.java)

    @Test
    fun contextLoads() {
        logger.debug("Context loaded")
    }

}