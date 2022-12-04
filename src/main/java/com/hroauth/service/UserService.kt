package com.hroauth.service

import com.hroauth.client.UserClient
import com.hroauth.model.User
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userClient: UserClient
) : UserDetailsService {

    fun findByEmail(email: String): User {
        val response = userClient.findByEmail(email)
        if (response.statusCode != HttpStatus.OK || response.body == null) throw UsernameNotFoundException("Email not found")

        return response.body!!
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw IllegalArgumentException("Username is null")

        return findByEmail(username)
    }
}