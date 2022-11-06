package com.sggnology.jwtauthtest.common.config

import com.sggnology.jwtauthtest.common.filter.JwtAuthenticationFilter
import com.sggnology.jwtauthtest.common.provider.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{

        /**
         * 설명
         * - x-frame-options 헤더 disable
         * - clickjacking 공격을 막기 위해서 spring-security 가 자동적으로 켜둠
         * */
        http
            .headers().frameOptions().disable()

        http
            .csrf().disable()

        http
            .httpBasic().disable()

        http
            .authorizeRequests()
            .antMatchers("/test").authenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/**").permitAll()

        http
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }
}