package com.demo.users

import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {

    fun create(user: User): User {
        return userRepository.save(User(
                email = user.email,
                password = encryptPw(user.password)
        ))
    }

    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun findByEmailAndPassword(email: String, pw: String): Optional<User> {
        return if (pw.isEmpty())
            Optional.empty()
        else
            userRepository.findByEmailAndPassword(email, encryptPw(pw))
    }

    private fun encryptPw(pw: String): String {
        return pw // todo
    }
}