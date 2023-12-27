package org.group05.police.tools;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtGenerator {

    public static String generateToken(String username, String authority) {
        long expirationTimeMs = 600000; // 600 seconds -> 10 minutes
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMs);

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("authority", authority)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();

        return token;
    }

}
