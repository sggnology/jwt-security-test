package com.sggnology.jwtauthtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtAuthTestApplication

fun main(args: Array<String>) {
    runApplication<JwtAuthTestApplication>(*args)
}
