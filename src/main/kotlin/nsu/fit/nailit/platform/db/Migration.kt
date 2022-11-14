package nsu.fit.nailit.platform.db

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import javax.sql.DataSource

class FlywayDbMigrator(
    private val dataSource: DataSource,
    private val config: FlywayProperties
) {

    fun migrateDb() {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations(*config.locations.toTypedArray())
            .baselineOnMigrate(config.isBaselineOnMigrate)
            .baselineVersion(config.baselineVersion)
            .load()
        flyway.migrate()
    }

}