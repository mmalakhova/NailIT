package nsu.fit.nailit.core.client.web

import nsu.fit.nailit.core.client.ClientsAppointmentDto
import nsu.fit.nailit.core.client.ClientsService
import nsu.fit.nailit.core.client.model.Client
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
@Validated
class ClientsController(
    private val clientsService: ClientsService
) {

    /**
     * Получение информации о клиенте для личного кабинета
     */
    @GetMapping("/{clientId}")
    fun getClientInfo(
        @PathVariable clientId: Long
    ): Client {
        return clientsService.getClientInfo(clientId)
    }

    /**
     * Получение списка записей по клиенту
     */
    @GetMapping("/appointments/{clientId}")
    fun getAppointmentsByClient(
        @PathVariable clientId: Long
    ): List<ClientsAppointmentDto> {
        return clientsService.getAppointmentsByClient(clientId)
    }
}