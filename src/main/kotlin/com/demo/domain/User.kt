package com.demo.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

// data class should not be used
// https://stackoverflow.com/questions/35847763/kotlin-data-class-bean-validation-jsr-303

@Entity
class User(
        @Column(unique = true)
        @get:Email(message = "email not valid!")
        val email: String,

        @get:NotBlank(message = "pw should not be blank!")
        var password: String,

        @ManyToMany @JoinColumn
        var roles: MutableCollection<Role> = mutableListOf(), // todo

        @Id @GeneratedValue
        val id: Long? = null)

@Entity
class Role(
        @Column(unique = true)
        val name: String,

        @Id @GeneratedValue
        val id: Long? = null)