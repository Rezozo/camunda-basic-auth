package com.example.workflow.config

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthManager(
    private val userDetailsService: UserDetailsService
) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials

        val userDetails = userDetailsService.loadUserByUsername(username)

        if (userDetails == null || !password.equals(userDetails.password))
            throw BadCredentialsException("Invalid login or password")

        return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
    }
}