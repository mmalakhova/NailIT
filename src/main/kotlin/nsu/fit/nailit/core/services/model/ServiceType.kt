package nsu.fit.nailit.core.services.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("service_types")
data class ServiceType(
    @Id
    val id: Long = 0,
    val title: String
)