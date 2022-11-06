package com.sggnology.jwtauthtest.model.entity

import javax.persistence.*

@Entity
@Table
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    lateinit var username: String

    lateinit var password: String
}