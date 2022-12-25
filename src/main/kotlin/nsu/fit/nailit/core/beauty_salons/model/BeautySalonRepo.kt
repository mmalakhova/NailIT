package nsu.fit.nailit.core.beauty_salons.model

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, value = "databaseTransactionManager")
interface BeautySalonRepo : CrudRepository<BeautySalon, Long>, PagingAndSortingRepository<BeautySalon, Long> {

    @Query("select * from beauty_salons bs where bs.name =  :name or :name is null")
    fun getBeautySalonsByName(name: String?): List<BeautySalon>

    fun getBeautySalonById(id: Long): BeautySalon
}