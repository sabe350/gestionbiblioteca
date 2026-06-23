package com.datalibro.registrolibrousr.security;


import java.util.Date;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtService {

    private final String SECRET="12a1101dKykczpWXofifO4FHbfgT3c3nLptrKPUM9wEOOqwYLyTYU233ZUj2";

    public String generatedToken(String username){
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+86400000))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public String exactUsername(String token){
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
