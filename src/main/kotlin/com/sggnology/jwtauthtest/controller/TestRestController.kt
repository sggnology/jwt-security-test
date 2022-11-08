package com.sggnology.jwtauthtest.controller

import com.sggnology.jwtauthtest.common.provider.JwtTokenProvider
import com.sggnology.jwtauthtest.repository.MemberRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("")
class TestRestController(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    @GetMapping("/test")
    fun test(
        httpServletRequest: HttpServletRequest
    ): String{
        return "<h1>test 입니다.</h1>"
    }

    @PostMapping("/login")
    fun login(
        username: String,
    ): String{
        val member = memberRepository.findByUsername(username).orElseThrow{UsernameNotFoundException("그런거 없음")}

        return jwtTokenProvider.createToken(member.username, listOf(member.authority))
    }
}