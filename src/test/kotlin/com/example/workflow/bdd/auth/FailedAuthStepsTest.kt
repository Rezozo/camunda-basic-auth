package com.example.workflow.bdd.auth

import com.example.workflow.model.request.AuthenticationBody
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import kotlin.test.assertEquals


class FailedAuthStepsTest(
    private val restTemplate: TestRestTemplate
) {

    @When("I enter incorrect login as {string} and password as {string}")
    fun i_enter_incorrect_login_and_password(email: String, password: String) {
        val authRequest= AuthenticationBody(email, password)

        val response: ResponseEntity<String> = restTemplate.postForEntity(
            "/auth",
            authRequest
        )

        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)
    }

    @Then("Authentication failed")
    fun authentication_failed() {
        val response = restTemplate.exchange(
            "/test",
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<String>() {}
        )

        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)
    }
}