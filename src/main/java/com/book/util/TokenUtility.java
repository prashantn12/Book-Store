package com.book.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class  TokenUtility {

    private static final String SECRET = "SecretKey";
    private static final long EXPIRATION_TIME = 600_000; // 15 minutes

    public String createToken(Long user_id, String role) {
        System.out.println("Create token");
        return JWT.create()
                .withClaim("emp_id", user_id)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Long decodeToken(String token) {
        System.out.println("decode");
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("emp_id")
                .asLong();
    }

    public String getRoleFromToken(String token) {
        System.out.println("get role");
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }

    public Long getEmpIdFromToken(String token) {
        System.out.println("get  id from token");
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("emp_id")
                .asLong();
    }

    // Method to extract the expiration date from the token
    public Date getExpirationFromToken(String token) {
        System.out.println("get expireation from token");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return decodedJWT.getExpiresAt();
    }
}

