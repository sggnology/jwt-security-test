package com.sggnology.jwtauthtest.common.auth

import com.sggnology.jwtauthtest.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        val member = memberRepository.findByUsername(username!!).orElseThrow(Supplier {UsernameNotFoundException("잘못된 아이디입니다.")})

        return CustomUserDetails(member)
    }
}