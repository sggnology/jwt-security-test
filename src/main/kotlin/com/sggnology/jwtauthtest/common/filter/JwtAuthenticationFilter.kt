package com.sggnology.jwtauthtest.common.filter

import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class JwtAuthenticationFilter: GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        TODO("Not yet implemented")
    }
}