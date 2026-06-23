package com.ncasanovas.jwt_token.service;


import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.ncasanovas.jwt_token.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshTokenExpiration;

  public String generateToken(final User user) {
    return buildToken(user, jwtExpiration);
  }

  public String generateRefreshToken(final User user) {
    return buildToken(user, refreshTokenExpiration);
  }

  public String extractUsername(final String token) {
    final Claims jwtToken = Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return jwtToken.getSubject();
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

  public boolean isValidToken(final String token, final User user) {
    final String username = extractUsername(token);
    return (username.equals(user.getEmail())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(final String token) {
    return extractExpiration(token).before(new Date(System.currentTimeMillis()));

  }

  public Date extractExpiration(final String token) {
    final Claims jwtToken = Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return jwtToken.getExpiration();
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
