package com.ncasanovas.jwt_token.service;

import java.sql.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.ncasanovas.jwt_token.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token-expiration}")
  private long refreshTokenExpiration;

  public String generateToken(final User user) {
    return buildToken(user, jwtExpiration);
  }

  public String generateRefreshToken(final User user) {
    return buildToken(user, refreshTokenExpiration);
  }

  private String buildToken(final User user, final long expiration) {
    return Jwts.builder()
        .id(user.getId().toString())
        .claims(Map.of("name", user.getName())) // Additional information
        .subject(user.getEmail()) // Identification of user
        .issuedAt(new Date(System.currentTimeMillis())) // Date of token generation
        .expiration(new Date(System.currentTimeMillis() + expiration)) // Date of token expiration
        .signWith(getSignInKey()) // Sign the token with the secret key
        .compact();

  }

  /**
   * Transforms the Base64 encoded secret key into a SecretKey object
   * suitable for HMAC-SHA algorithms.
   * * @return The prepared SecretKey for cryptographic signing.
   */
  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
