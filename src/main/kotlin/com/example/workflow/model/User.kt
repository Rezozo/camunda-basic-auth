package com.example.workflow.model

import com.example.workflow.enums.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private val id: Long?,
    @NotBlank(message = "Email is mandatory")
    private val email: String,
    @NotBlank(message = "Password is mandatory")
    private val password: String,
    private val role: Role
) : UserDetails {

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role.toString()))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
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
}