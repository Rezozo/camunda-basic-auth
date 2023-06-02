package com.example.workflow.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authManager: AuthManager
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .authenticationManager(authManager)
            .authorizeHttpRequests()
            .antMatchers("/test").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(
                BasicAuthenticationFilter(authManager),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .logout().permitAll()
            .and()
            .build()
    }
}