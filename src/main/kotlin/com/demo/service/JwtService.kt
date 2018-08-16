package com.demo.service

import com.demo.domain.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    @Value("\${jwt.secret}")
    val secret: String = "mySecret"

    @Value("\${jwt.expiration}")
    val expiration: Int = 600 // 10 minutes

    val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)!! // todo not the best idea: what if the app reset?

    fun create(user: User): String {

        // todo exp different per endpoint ?? in this case add field that define endpoint (encrypt)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, expiration)

        return Jwts.builder()
                .setSubject(user.id.toString())
                .setIssuedAt(Date()) // consider using this instead of (or with) exp, and validate manually (iat + 10 min < now )
                .setExpiration(calendar.time)
                .setNotBefore(Date())
                .signWith(secretKey)
                .compact()
    }

    fun getJwtClaims(jwt: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(getJwtFromHeader(jwt))
    }

    private fun getJwtFromHeader(jwtHeader: String) : String {
        return jwtHeader.split(" ")[1] // todo better
    }
}