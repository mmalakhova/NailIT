package nsu.fit.nailit.core.masters

import nsu.fit.nailit.core.masters.model.MastersRepo
import org.springframework.stereotype.Service

@Service
class MastersService(
    private val mastersRepo: MastersRepo
) {
}