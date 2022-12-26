package nsu.fit.nailit.core.auth.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("roles")
data class Role(
    @Id
    val id: Long,
    val name: String
)