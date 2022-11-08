package com.sggnology.jwtauthtest.common.provider

import com.sggnology.jwtauthtest.common.auth.CustomUserDetailsService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val customUserDetailsService: CustomUserDetailsService
) {

    private var secretKey: String = "jwt-request-test"

    private val tokenValidTime = 30 * 60 * 1000L

    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(userId: String, roles: List<String>): String {
        val claims = Jwts.claims()
        claims.subject = userId
        claims["roles"] = roles

        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getUserId(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun getAuthentication(token: String): Authentication {
        val customUserDetails = customUserDetailsService.loadUserByUsername(this.getUserId(token))
        return UsernamePasswordAuthenticationToken(customUserDetails, "", customUserDetails.authorities)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("authorization")
    }

    fun validateToken(jwtToken: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            val isNotExpired = claims.body.expiration.after(Date())
            return isNotExpired
        } catch (e: Exception) {
            return false
        }
    }
}