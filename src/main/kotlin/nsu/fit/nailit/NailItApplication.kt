package nsu.fit.nailit

import nsu.fit.nailit.core.DatabaseConfig
import nsu.fit.nailit.app.web.WebConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(WebConfig::class, DatabaseConfig::class)
@SpringBootApplication(exclude = [FlywayAutoConfiguration::class])
class NailItApplication

fun main(args: Array<String>) {
    runApplication<NailItApplication>(*args)
}
