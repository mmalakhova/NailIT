package nsu.fit.nailit.core.services.model

import nsu.fit.nailit.core.services.ServiceDto
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional


@Transactional(readOnly = false, value = "databaseTransactionManager")
interface ServicesRepo : CrudRepository<Service, Long> {

    @Query(
        """select s.id, s.title, to_char(s.time_estimate, 'HH24:MI:SS') as estimate, s.price, st.title as service
        from beauty_salons bs inner join masters m on bs.id = m.beauty_salon_id 
        inner join service s on m.id = s.master_id
        inner join service_types st on st.id = s.service_type_id
        where bs.id = :beautySalonId
    """
    )
    fun getServiceByBeautySalon(beautySalonId: Long): List<ServiceDto>

    @Query(
        """
        select * from service_types
    """
    )
    fun getServiceTypes(): List<ServiceType>
}
