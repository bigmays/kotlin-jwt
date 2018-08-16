package com.demo.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class User(
        @Column(unique = true)
        @Email
        val email: String,

        @NotBlank
        var password: String,

        @ManyToMany @JoinColumn
        var roles: MutableCollection<Role> = mutableListOf(), // todo

        @Id @GeneratedValue
        val id: Long? = null) // todo test w/out ?=null

@Entity
data class Role(
        @Column(unique = true)
        val name: String,

        @Id @GeneratedValue
        val id: Long? = null)