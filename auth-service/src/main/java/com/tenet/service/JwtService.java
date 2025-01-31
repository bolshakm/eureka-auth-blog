package com.tenet.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String KEY = "HRXRMhnBSvXoI5u696Pkc4AaasVLQfkC3QfyAEyZBk1ZpI71IdtTB5bYCkN7JuUBwxVDjxinlkMp6COqGP+av8kBrR6wxhI8W3ElyPlQfwQf7PT11bftVxAMUuSj8Xmm4agDOjU3axxbiQ7vkAkZyna+/NGEXAAbiq6tVNENRn5hqdDTHB9NbEqmDf3vvjKRU8Bbemt8bzz5CjoL88qp/rszSdo8ijPUZCciLSz06VkA1IDf9zv0lctAa/EtAuHJWe1auAqME4lyLrHyEcIYU4mo94FAY0f+eGh4DmzPHgxj79Hj5YjlHv7e/Fl2T0gnW5t3yvdVyzJr3qSML6VA==";

    JwtService() {
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractClaims(token));
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String userName = extractEmail(token);
            return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
