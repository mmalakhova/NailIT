package nsu.fit.nailit.core

import com.fasterxml.jackson.annotation.JsonIgnore
import nsu.fit.nailit.core.auth.model.ERole
import nsu.fit.nailit.core.client.model.Client
import org.springframework.security.core.GrantedAuthority

import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


class UserDetailsImpl(
    val id: Long,
    private val phone: String,
    @field:JsonIgnore
    private val password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return phone
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as UserDetailsImpl
        return Objects.equals(id, user.id)
    }

    companion object {
        private const val serialVersionUID = 1L
        fun build(user: Client): UserDetailsImpl {
            val authorities: List<GrantedAuthority> = user.roles.stream()
                .map { role -> SimpleGrantedAuthority(ERole.values()[role.roleId.toInt()].name) }
                .collect(Collectors.toList())

            println(authorities)
            return UserDetailsImpl(
                user.id,
                user.phone,
                user.password,
                authorities
            )
        }
    }
}