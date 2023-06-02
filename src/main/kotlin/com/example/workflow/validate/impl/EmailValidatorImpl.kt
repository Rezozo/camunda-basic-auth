package com.example.workflow.validate.impl

import com.example.workflow.service.UserService
import com.example.workflow.validate.EmailValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailValidatorImpl(
    private val userService: UserService
) : ConstraintValidator<EmailValidator, String> {

    override fun isValid(email: String, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        if (email.contains("@"))
            return !userService.isExist(email)
        return false
    }
}