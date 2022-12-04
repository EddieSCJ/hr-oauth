package com.hroauth.model

import org.springframework.security.core.GrantedAuthority

class Role(
    var id: Long? = null,
    private val roleName: String
) : GrantedAuthority {
    override fun getAuthority() = roleName
}