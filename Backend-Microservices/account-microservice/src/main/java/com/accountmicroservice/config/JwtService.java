package com.accountmicroservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final Environment environment;



    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateExtraClaimsToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateForgotPasswordToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateToken( UserDetails user) {
        return generateExtraClaimsToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(environment.getProperty("jwt.secret"));
        return Keys.hmacShaKeyFor(keyBytes);
    }




    //    public String extractEmail(String token) {
//        final Claims claims = extractAllClaims(token);
//        String email = (String) claims.get("email");
//        return email;
//    }

    //    public String generateForgotPasswordToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//
//        // Add custom claims
//        claims.put("firstName", user.getFirstName());
//        claims.put("lastName", user.getLastName());
//        claims.put("email", user.getEmail());
//        claims.put("role", user.getRole());
//
//        return Jwts.builder().setSubject(user.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
}
