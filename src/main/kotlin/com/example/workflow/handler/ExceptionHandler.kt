package com.example.workflow.handler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    private fun handleNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errMessage = Objects.requireNonNull(ex.fieldError)?.defaultMessage
        return ResponseEntity.badRequest().body(errMessage)
    }
}