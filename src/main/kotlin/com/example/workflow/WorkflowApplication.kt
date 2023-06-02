package com.example.workflow

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableProcessApplication
class WorkflowApplication

fun main(args: Array<String>) {
	runApplication<WorkflowApplication>(*args)
}
