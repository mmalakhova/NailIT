package nsu.fit.nailit.core.beauty_salons.web

import nsu.fit.nailit.core.beauty_salons.BeautySalonDto
import nsu.fit.nailit.core.beauty_salons.BeautySalonService
import nsu.fit.nailit.core.beauty_salons.model.BeautySalon
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/beauty_salons")
@Validated
class BeautySalonController(
    private val beautySalonService: BeautySalonService
) {

    @GetMapping
    fun getBeautySalons(
        @RequestParam name: String?,
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): List<BeautySalonDto> {
        return beautySalonService.getBeautySalons(name, lat, lon)
    }

    @GetMapping("/{id}")
    fun getBeautySalonById(
        @PathVariable id: Long
    ): BeautySalon {
        return beautySalonService.getBeautySalonById(id)
    }
}