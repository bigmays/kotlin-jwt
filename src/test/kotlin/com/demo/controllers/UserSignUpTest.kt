package com.demo.controllers

import com.demo.config.WebConfig
import com.demo.domain.User
import com.demo.services.JwtService
import com.demo.services.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import sun.net.httpserver.AuthFilter


@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
internal class UserSignUpTest {

    @Autowired lateinit var mockMvc: MockMvc
    @MockBean lateinit var userService: UserService
    @MockBean lateinit var jwtService: JwtService
    @MockBean lateinit var authFilter: AuthFilter
    @MockBean lateinit var webConfig: WebConfig

    @Test
    fun `Sign up - ok`() {

        val builder = signUpRequestBuilder()
                .content("{\"email\": \"test@gmail.com\", \"password\":\"123123\"}")

        mockMvc.perform(builder).andExpect(status().isOk)
    }

    @Test
    fun `Sign up - user already exists`() {
        val builder = signUpRequestBuilder()
                .content("{\"email\": \"test@gmail.com\", \"password\":\"123123\"}")

        given(this.userService.findByEmail("test@gmail.com"))
                .willReturn(Optional.of(User("test@gmail.com", "123123")))

        mockMvc.perform(builder).andExpect(status().isConflict)
    }

    @Test
    fun `Sign up - email not valid`() {
        val builder = signUpRequestBuilder()
                .content("{\"email\": \"test\", \"password\":\"123123\"}")

        mockMvc.perform(builder).andExpect(status().isBadRequest)
    }

    private fun signUpRequestBuilder(): MockHttpServletRequestBuilder {
        return MockMvcRequestBuilders
                .post("/sign-up")
                .contentType("application/json")
    }
}