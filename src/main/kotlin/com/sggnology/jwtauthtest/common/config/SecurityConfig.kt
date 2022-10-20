package com.sggnology.jwtauthtest.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(

) {



    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf().disable()

        http
            .httpBasic().disable()

        http
            .authorizeRequests()
            .antMatchers("/test").authenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/**").permitAll()

//        http
//            .addFilterBefore(JwtAuth)

        return http.build()
    }
}