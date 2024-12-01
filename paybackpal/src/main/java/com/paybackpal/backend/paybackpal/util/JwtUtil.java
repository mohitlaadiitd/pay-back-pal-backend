package com.paybackpal.backend.paybackpal.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${spring.authentication.jwt.secret.key}")
    private String JWT_SECRET_KEY;

    @Value("${spring.authentication.jwt.expiration.time}")
    private long JWT_EXPIRATION_TIME;

    public String getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }

    public String generateToken(String email) {
        SecretKey secretKey = new SecretKeySpec(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS512.getJcaName());

        logger.info("Secret Key " + secretKey);

        String jwt = Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

        logger.info("JWT generated for authentication purposes: {}", jwt);
        return jwt;
    }
}
