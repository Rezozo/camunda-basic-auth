package com.example.workflow.model.request

import com.example.workflow.validate.EmailValidator
import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class AuthenticationBody(
    @NotBlank(message = "Email is mandatory")
    @Size(min = 7, max = 70, message = "Email must be between 7 and 70 characters")
    @EmailValidator
    val email: String,

    @NotBlank(message = "Password is mandatory")
    val password: String
) : Serializable