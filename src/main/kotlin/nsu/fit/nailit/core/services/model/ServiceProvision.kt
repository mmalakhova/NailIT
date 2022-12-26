package nsu.fit.nailit.core.services.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("service_provision")
data class ServiceProvision(
    @Id
    val id: Long = 0,
    val title: String,
    val date: Instant,
    val price: Int,
    val available: Boolean = true,
    val client_id: Long?,
    val master_id: Long,
//    @Version
//    val version: Long = 0
)