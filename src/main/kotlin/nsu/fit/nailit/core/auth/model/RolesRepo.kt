package nsu.fit.nailit.core.auth.model

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, value = "databaseTransactionManager")
interface RolesRepo : CrudRepository<Role, Long> {
    fun findRoleByName(name: String): Role?
}
