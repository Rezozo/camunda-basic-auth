package com.example.workflow.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET])
    fun testMethod(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello!")
    }
}