package com.hroauth.model

import java.util.HashSet

data class User(
    var id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    val roles: Set<Role>? = HashSet()
)