package nsu.fit.nailit.core.services

import nsu.fit.nailit.core.services.model.ServiceProvision
import nsu.fit.nailit.core.services.model.ServiceType
import nsu.fit.nailit.core.services.model.ServicesRepo
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

data class ServiceDto(
    val id: Long,
    val title: String,
    val estimate: String,
    val price: Int,
    val service: String
)

data class ServiceProvisionDto(
    val id: Long,
    val title: String,
    val date: String,
    val price: Int,
    val available: Boolean,
    val client_id: Long?,
    val master_id: Long,
)

@Service
class ServicesService(
    private val servicesRepo: ServicesRepo,
    private val servicesProvisionRepo: ServicesProvisionRepo
) {

    fun getServicesByBeautySalonId(beautySalonId: Long): List<ServiceDto> {
        return servicesRepo.getServiceByBeautySalon(beautySalonId);
    }

    fun getServiceTypes(): List<ServiceType> {
        return servicesRepo.getServiceTypes()
    }

    fun signUpForService(serviceProvisionId: Long, userId: Long): ServiceProvision {
        val oldAppointment =
            servicesProvisionRepo.getServiceProvisionById(serviceProvisionId)
                ?: throw InvalidParameterException("No available appointment with this id")
        val updatedAppointment = oldAppointment.copy(available = false, client_id = userId)
        println(updatedAppointment)
        println(oldAppointment)
        return servicesProvisionRepo.save(updatedAppointment)
    }
}