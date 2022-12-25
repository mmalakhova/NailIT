package nsu.fit.nailit.core.client.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("clients")
data class Client(
    @Id
    val id: Long = 0,
    val name: String,
    val age: Int,
    val phone_number: String,
    val rate: Double,
    val gender_id: Int
)