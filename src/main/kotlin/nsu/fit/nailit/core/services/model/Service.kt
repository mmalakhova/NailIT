package nsu.fit.nailit.core.services.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("service")
data class Service(
    @Id
    val id: Long = 0,
    val title: String,
    val timeEstimate: Instant,
    val price: Int,
    val masterId: Int,
    val serviceTypeId: Long
)