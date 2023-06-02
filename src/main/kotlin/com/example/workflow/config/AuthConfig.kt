package com.example.workflow.config

import com.example.workflow.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthConfig {

    @Bean
    fun userDetailsService(repository: UserRepository): UserDetailsService? {
        return UserDetailsService {
            username -> repository.findByEmail(username)
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}