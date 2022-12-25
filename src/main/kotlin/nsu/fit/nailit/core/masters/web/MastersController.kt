package nsu.fit.nailit.core.masters.web

import nsu.fit.nailit.core.masters.MastersDto
import nsu.fit.nailit.core.masters.MastersService
import nsu.fit.nailit.core.masters.MastersWithAppointmentsDto
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/masters")
@Validated
class MastersController(
    private val mastersService: MastersService
) {

    /**
     * Получение списка всех мастеров
     */
    @GetMapping
    fun getMasters(
        @RequestParam name: String?,
        @RequestParam age: Int?,
        @RequestParam qualification: String?,
        @RequestParam(value = "pageSize", required = false, defaultValue = "100") pageSize: Int,
        @RequestParam(value = "pageIndex", required = false, defaultValue = "1") pageIndex: Int

    ): MastersDto {
        return mastersService.getMastersByFilter(
            name,
            age,
            qualification,
            PageRequest.of(pageSize * (pageIndex - 1), pageSize)
        )
    }

    @GetMapping("/{serviceId}/provision")
    fun getMastersWithAvailableAppointments(
        @PathVariable serviceId: Long
    ): MutableList<MastersWithAppointmentsDto> {
        return mastersService.getMastersWithAvailableAppointments(serviceId)
    }

}