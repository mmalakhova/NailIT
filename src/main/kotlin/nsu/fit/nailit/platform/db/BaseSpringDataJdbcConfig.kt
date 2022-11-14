package nsu.fit.nailit.platform.db

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.data.jdbc.core.convert.*
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext
import org.springframework.data.relational.core.dialect.Dialect
import org.springframework.data.relational.core.dialect.PostgresDialect
import org.springframework.data.relational.core.mapping.NamingStrategy
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations

@Configuration
class BaseSpringDataJdbcConfig {

    @Bean
    fun jdbcDialect(): Dialect {
        return PostgresDialect.INSTANCE
    }

    @Bean
    fun customConversions(): JdbcCustomConversions {
        return JdbcCustomConversions()
    }

    @Bean
    fun jdbcMappingContext(
        customConversions: JdbcCustomConversions
    ): JdbcMappingContext {
        val mappingContext = JdbcMappingContext(NamingStrategy.INSTANCE)
        mappingContext.setSimpleTypeHolder(customConversions.simpleTypeHolder)
        return mappingContext
    }

    @Bean
    fun jdbcConverter(
        mappingContext: JdbcMappingContext,
        operations: NamedParameterJdbcOperations,
        @Lazy relationResolver: RelationResolver,
        conversions: JdbcCustomConversions,
        dialect: Dialect
    ): JdbcConverter {
        val jdbcTypeFactory = DefaultJdbcTypeFactory(operations.jdbcOperations)
        return BasicJdbcConverter(
            mappingContext, relationResolver, conversions, jdbcTypeFactory,
            dialect.identifierProcessing
        )
    }

}