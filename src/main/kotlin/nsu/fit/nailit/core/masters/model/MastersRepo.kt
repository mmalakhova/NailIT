package nsu.fit.nailit.core.masters.model

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, value = "databaseTransactionManager")
interface MastersRepo : CrudRepository<Master, Long>, PagingAndSortingRepository<Master, Long>