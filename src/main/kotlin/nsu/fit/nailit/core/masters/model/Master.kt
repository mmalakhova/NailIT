package nsu.fit.nailit.core.masters.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("masters")
data class Master(
    @Id
    val id: Long = 0,
    val name: String,
    val age: Int,
    val phoneNumber: String,
    val rate: Float,
    val qualification: String,
    val photo_url: String?,
    val status: String?,
    val genderId: Int,
    val beautySalonId: Int,
    val masterCategoryId: Int
)