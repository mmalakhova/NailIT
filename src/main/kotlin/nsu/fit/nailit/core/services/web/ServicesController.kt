package nsu.fit.nailit.core.services.web

import nsu.fit.nailit.core.services.ServiceDto
import nsu.fit.nailit.core.services.ServicesService
import nsu.fit.nailit.core.services.model.ServiceProvision
import nsu.fit.nailit.core.services.model.ServiceType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services")
@Validated
class ServicesController(
    private val servicesService: ServicesService
) {
    @GetMapping("/{beautySalonId}")
    fun getServicesByBeautySalon(
        @PathVariable beautySalonId: Long
    ): List<ServiceDto> {
        return servicesService.getServicesByBeautySalonId(beautySalonId)
    }

    @GetMapping("/types")
    fun getServiceTypes(): List<ServiceType> {
        return servicesService.getServiceTypes()
    }

    @PostMapping("/enroll/{serviceProvisionId}")
    fun signUpForService(
        @PathVariable serviceProvisionId: Long,
        @RequestParam userId: Long
    ): ServiceProvision {
        return servicesService.signUpForService(serviceProvisionId, userId)
    }
}