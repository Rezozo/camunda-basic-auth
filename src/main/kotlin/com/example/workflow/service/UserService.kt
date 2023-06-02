package com.example.workflow.service

import com.example.workflow.model.User

interface UserService {
    fun getByEmail(email: String): User?

    fun save(user: User)

    fun isExist(email: String): Boolean
}