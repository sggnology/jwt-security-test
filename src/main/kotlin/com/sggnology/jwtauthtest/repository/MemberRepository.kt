package com.sggnology.jwtauthtest.repository

import com.sggnology.jwtauthtest.model.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun findByUsername(username: String): Optional<Member>
}