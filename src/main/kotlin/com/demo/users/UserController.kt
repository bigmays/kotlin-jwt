package com.demo.users

import com.demo.jwt.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController(val userService: UserService, val jwtService: JwtService) {

    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody user: User): ResponseEntity<Any> {
        return if (userService.findByEmail(user.email).isPresent)
            ResponseEntity("User already exists", HttpStatus.CONFLICT)
        else
            ResponseEntity.ok(jwtService.create(userService.create(user)))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody user: User): ResponseEntity<Any> {
        return if (userService.findByEmailAndPassword(user.email, user.password).isPresent)
            ResponseEntity.ok(jwtService.create(user))
        else
            ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    // Example: To call this method you need a valid jwt
    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Test ok")
    }

}