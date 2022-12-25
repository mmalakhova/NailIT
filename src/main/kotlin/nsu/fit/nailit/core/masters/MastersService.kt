package nsu.fit.nailit.core.masters

import nsu.fit.nailit.core.masters.model.Master
import nsu.fit.nailit.core.masters.model.MastersRepo
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


data class MastersDto(
    val masters: List<Master>,
    val meta: MetaDataResponse
) {
    constructor(result: List<Master>, mastersCount: Long, page: Pageable) : this(
        result,
        MetaDataResponse(mastersCount, page.pageSize)
    )
}

data class MetaDataResponse(
    val totalItems: Long,
    val pageSize: Int
)

data class MastersWithAppointmentsDto(
    val master: Master,
    val appointmentDtos: List<AppointmentDto>
)

data class AppointmentDto(
    val id: Long,
    val title: String,
    val date: String,
    val price: Int
)


@Service
class MastersService(
    private val mastersRepo: MastersRepo
) {

    fun getMastersByFilter(name: String?, age: Int?, qualification: String?, pageRequest: PageRequest): MastersDto {
        val mastersCount = mastersRepo.getMastersCountByFilter(name, age, qualification)
        val result = mastersRepo.getMastersByFilterPaginated(
            name,
            age,
            qualification,
            pageRequest.pageSize,
            pageRequest.pageNumber
        )
        return MastersDto(result, mastersCount, pageRequest)
    }

    fun getMastersWithAvailableAppointments(serviceId: Long): MutableList<MastersWithAppointmentsDto> {
        val mastersByService = mastersRepo.getMastersByService(serviceId)
        val result = mutableListOf<MastersWithAppointmentsDto>()
        mastersByService.forEach {
            val appointments = mastersRepo.getAppointmentsByMaster(it.id)
            val mastersWithAppointmentsDto = MastersWithAppointmentsDto(it, appointments)
            result.add(mastersWithAppointmentsDto)
        }
        return result
    }
}