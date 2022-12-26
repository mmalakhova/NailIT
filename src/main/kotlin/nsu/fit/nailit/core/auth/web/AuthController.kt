package nsu.fit.nailit.core.auth.web

import com.fasterxml.jackson.annotation.JsonProperty
import nsu.fit.nailit.core.UserDetailsImpl
import nsu.fit.nailit.core.auth.model.ERole
import nsu.fit.nailit.core.auth.model.Role
import nsu.fit.nailit.core.auth.model.RolesRepo
import nsu.fit.nailit.core.client.model.Client
import nsu.fit.nailit.core.client.model.ClientsRepo
import nsu.fit.nailit.core.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.validation.Valid


data class LoginRequest(
    @JsonProperty("phoneNumber")
    val phoneNumber: String,
    @JsonProperty("password")
    val password: String
)

data class SignupRequest(
    @JsonProperty("phoneNumber")
    val phoneNumber: String,
    @JsonProperty("password")
    val password: String,
    @JsonProperty("role")
    val role: Set<String>
)

data class UserInfoResponse(
    val id: Long,
    val phoneNumber: String,
    val roles: List<String>
)

data class JwtResponse(
    val id: Long,
    val token: String,
    val type: String = "Bearer",
    val phoneNumber: String,
    val roles: List<String>
)


//@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/auth")
class AuthController(
    private val clientsRepo: ClientsRepo,
    private val rolesRepo: RolesRepo,
    private val encoder: PasswordEncoder,
    private val jwtUtils: JwtUtils,
    private val authenticationManager: AuthenticationManager
) {


    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        println(loginRequest)
        println(
            UsernamePasswordAuthenticationToken(loginRequest.phoneNumber, loginRequest.password)
        )
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.phoneNumber, loginRequest.password)
        )

        println(authentication)

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok<Any>(
            JwtResponse(
                token = jwt,
                id = userDetails.id,
                phoneNumber = userDetails.username,
                roles = roles
            )
        )

    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody @Valid signUpRequest: SignupRequest): ResponseEntity<*> {
        println(signUpRequest)
        if (clientsRepo.existsByPhone(signUpRequest.phoneNumber)) {
            return ResponseEntity.badRequest().body<Any>("Error: Username is already taken!")
        }

        // Create new user's account
        val user = Client(
            phone = signUpRequest.phoneNumber,
            password = encoder.encode(signUpRequest.password)
        )

        println(signUpRequest)

        val strRoles: Set<String> = signUpRequest.role
        val roles: MutableSet<Role> = HashSet()
        strRoles.forEach(Consumer { role: String? ->
            when (role) {
                "admin" -> {
                    val adminRole: Role = rolesRepo.findRoleByName(ERole.ROLE_ADMIN.name)
                        ?: throw RuntimeException("Error: Role is not found.")
                    roles.add(adminRole)
                }

                else -> {
                    val userRole: Role = rolesRepo.findRoleByName(ERole.ROLE_CLIENT.name)
                        ?: throw RuntimeException("Error: Role is not found.")
                    roles.add(userRole)
                }
            }
        })

        roles.forEach { role -> user.addRole(role) }
        println(roles)
        println(user)
        clientsRepo.save(user)
        return ResponseEntity.ok<Any>("User registered successfully!")
    }

    @PostMapping("/signout")
    fun logoutUser(): ResponseEntity<*> {
        return ResponseEntity.ok().body<Any>("You've been signed out!")
    }
}