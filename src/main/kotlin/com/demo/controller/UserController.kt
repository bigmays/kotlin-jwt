package com.demo.controller

import com.demo.service.JwtService
import com.demo.domain.User
import com.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(val userService: UserService, val jwtService: JwtService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: User): ResponseEntity<Any> {
        return if (userService.findByEmail(user.email).isPresent)
            ResponseEntity("User already exists", HttpStatus.CONFLICT)
        else
            ResponseEntity.ok(jwtService.create(userService.create(user)))
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<Any> {
        return if (userService.findByEmailAndPassword(user.email, user.password).isPresent)
            ResponseEntity.ok(jwtService.create(user))
        else
            ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Test ok")
    }

}