package com.a2.swifting_fitness.common.config;

import com.a2.swifting_fitness.common.enums.UserRole;
import com.a2.swifting_fitness.features.auth.model.JWTExtracted;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "58d382612c47f7011d8d852e630395c8bd835f724ae8c1681aaabbcd27ca0fac";

    public String extractUID(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public JWTExtracted extractFromToken(String token) {
        var value = extractAllClaims(token);
        return JWTExtracted.builder()
                .uid(value.get("sub").toString())
                .role(UserRole.user)
                .build();
        //Role.valueOf(value.get(roleKey).toString())
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails details) {
        var extracted = extractFromToken(token);
        return extracted.getUid().equals(details.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        var expiration = extractExpiration(token);
        return expiration == null || extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        token=token.replaceAll("Bearer ","");
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private SecretKey getKey() {
        var keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
