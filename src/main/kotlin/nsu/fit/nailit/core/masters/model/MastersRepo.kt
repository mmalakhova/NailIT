package nsu.fit.nailit.core.masters.model

import nsu.fit.nailit.core.masters.AppointmentDto
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, value = "databaseTransactionManager")
interface MastersRepo : CrudRepository<Master, Long>, PagingAndSortingRepository<Master, Long> {

    @Query(
        """
           SELECT count(*) FROM masters m WHERE (m.name = :name or :name is NULL) 
           AND (m.age = :age or :age is NULL) 
           AND (m.qualification = :qualification or :qualification is NULL) 
        """
    )
    fun getMastersCountByFilter(name: String?, age: Int?, qualification: String?): Long

    @Query(
        """
            SELECT * FROM masters m WHERE (m.name = :name or :name is NULL) 
           AND (m.age = :age or :age is NULL) 
           AND (m.qualification = :qualification or :qualification is NULL) 
           LIMIT :pageSize OFFSET :startIndex
        """
    )
    fun getMastersByFilterPaginated(
        name: String?,
        age: Int?,
        qualification: String?,
        pageSize: Int,
        startIndex: Int
    ): List<Master>

    @Query(
        """
        select * from masters inner join service s on masters.id = s.master_id
        where s.id = :serviceId
    """
    )
    fun getMastersByService(serviceId: Long): List<Master>

    @Query(
        """
            select sp.id, sp.title, 
            sp.date::varchar, 
            sp.price
            from service_provision sp 
            inner join masters m on m.id = sp.master_id
            where m.id = :masterId and sp.available = true
        """
    )
    fun getAppointmentsByMaster(masterId: Long): List<AppointmentDto>
}