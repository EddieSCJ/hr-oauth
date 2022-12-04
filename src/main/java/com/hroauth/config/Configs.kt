package com.hroauth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
open class Configs {
    @Bean
    open fun bcryptEncoder() = BCryptPasswordEncoder()

    @Bean
    open fun accessTokenConverter(): JwtAccessTokenConverter {
        val tokenConverter = JwtAccessTokenConverter()
        tokenConverter.setSigningKey("MY-SECRET-KEY")

        return tokenConverter
    }

    @Bean
    open fun tokenStore() = JwtTokenStore(accessTokenConverter())
}