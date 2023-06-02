package com.example.workflow.controller

import com.example.workflow.model.request.AuthenticationBody
import com.example.workflow.service.AuthenticationService
import com.example.workflow.service.impl.CamundaProcessImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(
    private val authenticationService: AuthenticationService,
    private val camundaProcessImpl: CamundaProcessImpl
) {

    @RequestMapping(value = ["/auth"], method = [RequestMethod.POST])
    fun authenticationAcc(@RequestBody authenticationBody: AuthenticationBody): ResponseEntity<AuthenticationBody> {
        camundaProcessImpl.startProcess("Auth-process", authenticationBody)
        return ResponseEntity.ok(authenticationService.authentication(authenticationBody))
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun registerAcc(@Valid @RequestBody request: AuthenticationBody): ResponseEntity<String> {
        authenticationService.register(request)
        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/logout"], method = [RequestMethod.GET])
    fun logout(): ResponseEntity<String> {
        return ResponseEntity.ok().build()
    }
}