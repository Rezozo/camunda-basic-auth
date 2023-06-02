package com.example.workflow.service.impl

import com.example.workflow.enums.Role
import com.example.workflow.model.User
import com.example.workflow.model.request.AuthenticationBody
import com.example.workflow.service.AuthenticationService
import com.example.workflow.service.UserService
import org.camunda.bpm.engine.delegate.BpmnError
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val userService: UserService,
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationService, JavaDelegate {

    override fun authentication(request: AuthenticationBody): AuthenticationBody {
        val login = request.email
        val password = request.password

        val userDetails: UserDetails? = userDetailsService.loadUserByUsername(login)

        if (userDetails != null && passwordEncoder.matches(password, userDetails.password))
            return AuthenticationBody(userDetails.username, userDetails.password)
        else
            throw BadCredentialsException("Invalid login or password")
    }

    override fun register(request: AuthenticationBody) {
        userService.save (
            User(null, request.email, passwordEncoder.encode(request.password), Role.User)
        )
    }

    override fun execute(execution: DelegateExecution) {
        val auth = execution.getVariable("authBody") as AuthenticationBody

        if (authentication(auth).email.isEmpty())
            throw BpmnError("BadCredentialsException","Invalid login or password")
    }
}