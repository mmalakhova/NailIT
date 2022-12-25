package nsu.fit.nailit.core.client

import nsu.fit.nailit.core.client.model.Client
import nsu.fit.nailit.core.client.model.ClientsRepo
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

data class ClientsAppointmentDto(
    val id: Long,
    val title: String,
    val date: String,
    val price: Int,
    val master: String,
    val address: String,
    val salon: String,
)

@Service
class ClientsService(
    private val clientsRepo: ClientsRepo
) {

    fun getClientInfo(clientId: Long): Client {
        return clientsRepo.getClientById(clientId) ?: throw InvalidParameterException("No client with such id")
    }

    fun getAppointmentsByClient(clientId: Long): List<ClientsAppointmentDto> {
        return clientsRepo.getAppointmentsByClient(clientId) ?: listOf()
    }
}