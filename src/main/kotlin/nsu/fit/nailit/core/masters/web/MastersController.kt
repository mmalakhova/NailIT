package nsu.fit.nailit.core.masters.web

import nsu.fit.nailit.core.masters.MastersService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun getMasters() {

    }
}