package nsu.fit.nailit.core.client.model

import nsu.fit.nailit.core.auth.model.Role
import nsu.fit.nailit.core.auth.model.RoleRef
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("clients")
data class Client(
    @Id
    val id: Long = 0,
    val name: String? = null,
    val age: Int? = null,
    val phone: String,
    val rate: Double? = null,
    val gender_id: Int? = null,
    val password: String,
    @MappedCollection(idColumn = "client_id")
    var roles: MutableSet<RoleRef> = mutableSetOf()
) {
    fun addRole(role: Role) {
        roles.add(RoleRef(role.id))
    }
}