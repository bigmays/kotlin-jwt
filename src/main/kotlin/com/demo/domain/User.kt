package com.demo.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

// @get annotation is used to validate correctly the values

@Entity
data class User(
        @Column(unique = true)
        @get:Email(message = "email not valid!")
        val email: String,

        @get:NotBlank(message = "pw should not be blank!")
        var password: String,

        @ManyToMany @JoinColumn
        var roles: MutableCollection<Role> = mutableListOf(), // todo

        @Id @GeneratedValue
        val id: Long = 0)

@Entity
data class Role(
        @Column(unique = true)
        val name: String,

        @Id @GeneratedValue
        val id: Long? = null)