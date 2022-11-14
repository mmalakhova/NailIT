package nsu.fit.nailit.core

import nsu.fit.nailit.platform.db.BaseSpringDataJdbcConfig
import nsu.fit.nailit.platform.db.FlywayDbMigrator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.*
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.TransactionManager
import javax.sql.DataSource

@Import(BaseSpringDataJdbcConfig::class)
@ComponentScan
@EnableJdbcRepositories(
    jdbcOperationsRef = "databaseJdbcOperations",
    transactionManagerRef = "databaseTransactionManager"
)
@EnableJdbcAuditing
@Configuration
class DatabaseConfig {

    @Bean(initMethod = "migrateDb")
    fun dbMigrator() = FlywayDbMigrator(dataSource(), flywayProps())

    @Bean
    @ConfigurationProperties(prefix = "nailit.datasource")
    fun dataSource(): DataSource =
        DataSourceBuilder.create()
            .build()

    @Bean
    @ConfigurationProperties(prefix = "nailit.flyway")
    fun flywayProps(): FlywayProperties =
        FlywayProperties()

    @Bean
    fun jdbcOperations(@Qualifier("dataSource") ds: DataSource): NamedParameterJdbcOperations =
        NamedParameterJdbcTemplate(ds)

    @Bean
    fun transactionManager(@Qualifier("dataSource") ds: DataSource): TransactionManager {
        return DataSourceTransactionManager(ds)
    }

}