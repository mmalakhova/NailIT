package nsu.fit.nailit.core.beauty_salons

import nsu.fit.nailit.core.beauty_salons.model.BeautySalon
import nsu.fit.nailit.core.beauty_salons.model.BeautySalonRepo
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sqrt

data class BeautySalonDto(
    val name: String,
    val address: String,
    val rate: Double,
    val distance: Double
)

@Service
class BeautySalonService(
    private val beautySalonRepo: BeautySalonRepo
) {

    fun getBeautySalons(name: String?, lat: Double, lon: Double): List<BeautySalonDto> {
        val beautySalons = beautySalonRepo.getBeautySalonsByName(name)
        println(beautySalons)
        val beautySalonsDtos = beautySalons.map {
            BeautySalonDto(it.name, it.address, it.rate, calculateDistance(lat, lon, it))
        }
        return beautySalonsDtos
    }

    fun getBeautySalonById(id: Long): BeautySalon {
        return beautySalonRepo.getBeautySalonById(id)
    }

    fun calculateDistance(lat: Double, lon: Double, bs: BeautySalon): Double {
        return 111.2 * sqrt(
            (lon.absoluteValue - bs.lon.toBigDecimal().abs().toDouble()) * (lon.absoluteValue - bs.lon.toBigDecimal()
                .abs().toDouble())
                    + (lat.absoluteValue - bs.lat.toBigDecimal().abs()
                .toDouble()) * cos(Math.PI * lon.absoluteValue / 180) * (lat.absoluteValue - bs.lat.toBigDecimal().abs()
                .toDouble())
                    * cos(Math.PI * lon / 180)
        )
    }
}