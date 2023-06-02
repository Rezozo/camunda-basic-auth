package com.example.workflow.service

import com.example.workflow.model.request.AuthenticationBody
import org.camunda.bpm.engine.runtime.ProcessInstance

interface CamundaProcess {
    fun startProcess(title: String, authenticationBody: AuthenticationBody)

    fun completeFirstTask(processInstance: ProcessInstance)
}