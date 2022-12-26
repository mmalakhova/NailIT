package nsu.fit.nailit.core.client.model

import nsu.fit.nailit.core.client.ClientsAppointmentDto
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional


@Transactional(readOnly = false, value = "databaseTransactionManager")
interface ClientsRepo : CrudRepository<Client, Long> {

    fun getClientById(clientId: Long): Client?

    @Query(
        """
        SELECT sp.id, sp.title, sp.date::varchar, sp.price, 
        m.name as master, bs.address as address, bs.name as salon 
        FROM service_provision sp 
        INNER JOIN masters m ON m.id = sp.master_id
        INNER JOIN beauty_salons bs ON bs.id = m.beauty_salon_id
        WHERE sp.client_id = :clientId
    """
    )
    fun getAppointmentsByClient(clientId: Long): List<ClientsAppointmentDto>?

    fun existsByName(name: String): Boolean
    fun existsByPhone(phone: String): Boolean
    fun findByPhone(phone: String): Client?
}