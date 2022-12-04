package com.hroauth.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtAccessTokenConverter: JwtAccessTokenConverter,
    private val tokenStore: JwtTokenStore,
    private val authenticationManager: AuthenticationManager
) : AuthorizationServerConfigurerAdapter() {
    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security
            ?.tokenKeyAccess("permitAll()")
            ?.checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients
            ?.inMemory()
            ?.withClient("myappname123")
            ?.secret(passwordEncoder.encode("myappsecret123"))
            ?.scopes("read", "write")
            ?.authorizedGrantTypes("password")
            ?.accessTokenValiditySeconds(86400)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints
            ?.tokenStore(tokenStore)
            ?.authenticationManager(authenticationManager)
            ?.accessTokenConverter(jwtAccessTokenConverter)
    }
}