package com.example.workflow.service.impl

import com.example.workflow.model.User
import com.example.workflow.repository.UserRepository
import com.example.workflow.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun isExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}