package com.hroauth.client

import com.hroauth.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "hr-user", path = "/users")
interface UserClient {
     @GetMapping(value = ["/email/{email}"])
     fun findByEmail(@PathVariable("email") email: String): ResponseEntity<User>
}