package com.hroauth.model

import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet

class User(
    var id: Long? = null,
    val name: String,
    private val email: String,
    private val password: String,
    private val roles: Set<Role>? = HashSet()
) : UserDetails {
    override fun getAuthorities() = roles

    override fun isEnabled() = true

    override fun getUsername() = email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}