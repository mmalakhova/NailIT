package nsu.fit.nailit.core.beauty_salons.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("beauty_salons")
data class BeautySalon(
    @Id
    val id: Long,
    val name: String,
    val address: String,
    val rate: Double,
    val lat: String,
    val lon: String
)