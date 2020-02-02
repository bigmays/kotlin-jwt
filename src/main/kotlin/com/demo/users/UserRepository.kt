package com.demo.users

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String) : Optional<User>
    fun findByEmailAndPassword(email: String, password: String) : Optional<User>
}