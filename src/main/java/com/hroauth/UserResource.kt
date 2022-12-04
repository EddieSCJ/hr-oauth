package com.hroauth

import com.hroauth.model.User
import com.hroauth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

class UserResource(
    private val userService: UserService
) {

    @GetMapping(value = ["/search"])
    fun findByEmail(@RequestParam email: String): ResponseEntity<User> {
        return try {
            val user = userService.findByEmail(email)
            ResponseEntity.ok(user)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}