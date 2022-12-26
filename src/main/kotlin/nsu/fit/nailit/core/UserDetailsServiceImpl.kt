package nsu.fit.nailit.core

import nsu.fit.nailit.core.client.model.Client
import nsu.fit.nailit.core.client.model.ClientsRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl(
    private val clientsRepo: ClientsRepo
) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(phone_number: String): UserDetails? {
        val user: Client = clientsRepo.findByPhone(phone_number)
            ?: throw UsernameNotFoundException("User Not Found with username: $phone_number")
        return UserDetailsImpl.build(user)
    }
}