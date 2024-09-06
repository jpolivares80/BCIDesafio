package com.bci.desafio.desafio.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.security.Key;

import io.jsonwebtoken.security.Keys;

public class JwtVerifier {

  public static void verificarToken(String token, Key claveSecreta) {
    // Parsear el token y verificar la firma
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(claveSecreta)
      .build()
      .parseClaimsJws(token)
      .getBody();

    // Extraer las reclamaciones
    String subject = claims.getSubject();
    String roles = claims.get("roles", String.class);
    String email = claims.get("email", String.class);

    System.out.println("Subject: " + subject);
    System.out.println("Roles: " + roles);
    System.out.println("Email: " + email);
  }

  

  

  public static void main(String[] args) {
    JwtGenerator jwtGenerator = new JwtGenerator();
    String token = jwtGenerator.generarTokenConClaims();

    // Usamos la misma clave secreta para verificar el token
    Key claveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    verificarToken(token, claveSecreta);


    
  }
}
