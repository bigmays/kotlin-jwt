package com.demo.controller

import com.demo.domain.User
import com.demo.service.JwtService
import com.demo.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*


@ExtendWith(SpringExtension::class)
internal class UserControllerTest {

    @InjectMocks
    lateinit var userController: UserController

    @Mock
    lateinit var userService: UserService

    @Mock
    lateinit var jwtService: JwtService

    @Test
    fun signUp() {
        val user = User("test@gmail.com", "Matteo")
        `when`(userService.findByEmail(user.email)).thenReturn(Optional.empty())

        val result = userController.signUp(user)
        assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun login() {
    }

    @Test
    fun test1() {
    }

    @Test
    fun getJwtService() {
    }
}