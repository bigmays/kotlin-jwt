package com.demo.services

import com.demo.domain.User
import io.jsonwebtoken.JwtException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class JwtServiceTest {

    @Autowired
    lateinit var jwtService: JwtService

    @Test
    fun `Jwt not valid`() {
        val jwt = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTM0Njg0NzEwLCJleHAiOjE1MzQ2ODUzMTAsIm5iZiI6MTUzNDY4NDcxMH0.jTMmXUb2m6UMtXljrbH6bf9U5jK56vOg_dzwSsV7leM"

        assertFailsWith(JwtException::class) {
            jwtService.getJwtClaims(jwt)
        }
    }


    @Test
    fun `create a valid Jwt`() {
        val user = User("test@gmail.com", "123123")

        val jwtResult = jwtService.create(user)

        assertEquals(user.id.toString(), jwtService.getJwtClaims("Bearer $jwtResult").body.subject)
    }
}