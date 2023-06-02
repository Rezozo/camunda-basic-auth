package com.example.workflow.service.impl

import com.example.workflow.model.request.AuthenticationBody
import com.example.workflow.service.CamundaProcess
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.springframework.stereotype.Service

@Service
class CamundaProcessImpl(
    private val processEngine: ProcessEngine
): CamundaProcess {

    override fun startProcess(title: String, authenticationBody: AuthenticationBody) {
        val variables = HashMap<String, Any>()
        variables["authBody"] = authenticationBody

        val runtimeService: RuntimeService = processEngine.runtimeService
        val processInstance = runtimeService.startProcessInstanceByKey(title, variables)

        completeFirstTask(processInstance)
    }

    override fun completeFirstTask(processInstance: ProcessInstance) {
        val task: TaskService = processEngine.taskService
        val taskQuery = task.createTaskQuery().processInstanceId(processInstance.id)
        val currentTask = taskQuery.singleResult()

        task.complete(currentTask.id)
    }
}