package nsu.fit.nailit.core.services

import nsu.fit.nailit.core.services.model.ServiceProvision
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, value = "databaseTransactionManager")
interface ServicesProvisionRepo : CrudRepository<ServiceProvision, Long> {

    @Query(
        """
        SELECT *
        FROM service_provision 
        WHERE id = :serviceProvisionId AND available is true
    """
    )
    fun getServiceProvisionById(serviceProvisionId: Long): ServiceProvision?

}
