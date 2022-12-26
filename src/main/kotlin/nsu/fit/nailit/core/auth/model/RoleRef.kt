package nsu.fit.nailit.core.auth.model

import org.springframework.data.relational.core.mapping.Table

@Table("client_roles")
data class RoleRef(

    val roleId: Long
)
