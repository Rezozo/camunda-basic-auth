package com.example.workflow.service

import com.example.workflow.model.request.AuthenticationBody

interface AuthenticationService {
    fun authentication(request: AuthenticationBody): AuthenticationBody

    fun register(request: AuthenticationBody)
}