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

class SuccessAuthStepsTest (
    private val restTemplate: TestRestTemplate
) {

    @Given("I am on the login page")
    fun i_am_on_the_login_page() {
        println("On the implemented page...")
    }

    @When("I enter login as {string} and password as {string}")
    fun i_enter_login_and_password(email: String, password: String) {
        val authRequest= AuthenticationBody(email, password)

        val response: ResponseEntity<String> = restTemplate.postForEntity(
            "/auth",
            authRequest
        )

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Then("I successfully logged in with header {string} and {string}")
    fun i_successfully_logged_in_with_header(email: String, encPassword: String) {
        val header = HttpHeaders()
        header.setBasicAuth(email, encPassword)

        val response = restTemplate.exchange(
            "/test",
            HttpMethod.GET,
            HttpEntity<HttpHeaders>(header),
            object : ParameterizedTypeReference<String>() {}
        )

        assertEquals(HttpStatus.OK, response.statusCode)
    }
}