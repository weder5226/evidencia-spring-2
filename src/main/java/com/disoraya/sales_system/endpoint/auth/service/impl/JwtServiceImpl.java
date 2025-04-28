package com.disoraya.sales_system.endpoint.auth.service.impl;

import com.disoraya.sales_system.endpoint.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
  @Value("${security.jwt.issuer}")
  private String ISSUER;

  @Value("${security.jwt.expiration-min}")
  private Long EXPIRATION_MIN;

  @Value("${security.jwt.secret-key}")
  private String SECRET_KEY;

  @Override
  public String generateToken(UserDetails user) {
    Date issuedAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MIN * 60 * 1000));
    String role = user.getAuthorities().stream()
        .findFirst()
        .map(GrantedAuthority::getAuthority)
        .map(r -> r.replaceFirst("ROLE_", ""))
        .orElse(null);

    return Jwts.builder()
        .claim("name", "Company Administrator")
        .claim("role", role)
        .issuer(ISSUER)
        .subject(user.getUsername())
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(generateKey())
        .compact();
  }

  @Override
  public Integer extractSubject(String jwt) {
    String sub = extractClaims(jwt).getSubject();
    try {
      return Integer.valueOf(sub);
    } catch (NumberFormatException e) {
      throw new JwtException("The 'subject' of the JWT is not an integer");
    }
  }

  private Claims extractClaims(String jwt) {
    return Jwts.parser()
        .verifyWith((SecretKey) generateKey())
        .build()
        .parseSignedClaims(jwt)
        .getPayload();
  }

  private Key generateKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }
}
