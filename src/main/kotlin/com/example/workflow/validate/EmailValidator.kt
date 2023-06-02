package com.example.workflow.validate

import com.example.workflow.validate.impl.EmailValidatorImpl
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailValidatorImpl::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EmailValidator(
    val message: String = "Invalid email",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
