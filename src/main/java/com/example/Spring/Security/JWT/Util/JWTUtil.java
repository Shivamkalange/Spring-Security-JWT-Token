package com.example.Spring.Security.JWT.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTUtil {

      //private final String SECRET_KEY="mysecretkey-12345-67890-abcdefg";
      private final String SECRET_KEY="a-string-secret-at-least-256-bits-long";
      private final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds
      private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

      public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(secretKey, Jwts.SIG.HS256)
                    .compact();

            // return "JWT token generated";
      }

}
