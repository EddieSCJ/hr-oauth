package com.hroauth.service

import com.hroauth.client.UserClient
import com.hroauth.model.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userClient: UserClient
) {

    fun findByEmail(email: String): User {
        val response = userClient.findByEmail(email)
        if (response.statusCode != HttpStatus.OK || response.body == null) throw IllegalArgumentException("Email not found")

        return response.body!!
    }
}