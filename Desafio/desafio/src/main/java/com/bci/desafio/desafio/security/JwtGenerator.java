package com.bci.desafio.desafio.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtGenerator {

  // Genera un JWT con una clave secreta
  public String generarToken() {
    // Clave secreta (en un entorno real, esto debería ser más robusto y seguro)
    Key claveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Fecha de expiración del token (1 hora)
    long expiracionMilis = 1000 * 60 * 60; // 1 hora en milisegundos
    Date fechaExpiracion = new Date(System.currentTimeMillis() + expiracionMilis);

    // Crear el token JWT
    return Jwts.builder()
      .setSubject("usuario123")         // Reclamo 'subject' (quién es el token)
      .setIssuer("tuapp.com")           // Reclamo 'issuer' (quién generó el token)
      .setIssuedAt(new Date())              // Fecha en que se generó el token
      .setExpiration(fechaExpiracion)       // Fecha de expiración
      .signWith(claveSecreta)               // Firma con clave secreta y algoritmo HS256
      .compact();                           // Genera el JWT como cadena
  }

  public String generarTokenConClaims() {
    Key claveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    long expiracionMilis = 1000 * 60 * 60; // 1 hora
    Date fechaExpiracion = new Date(System.currentTimeMillis() + expiracionMilis);

    return Jwts.builder()
      .setSubject("usuario123")
      .setIssuer("tuapp.com")
      .setIssuedAt(new Date())
      .setExpiration(fechaExpiracion)
      // Agregar reclamaciones personalizadas
      .claim("roles", "admin")               // Reclamación personalizada "roles"
      .claim("email", "usuario@example.com")  // Reclamación personalizada "email"
      .signWith(claveSecreta)
      .compact();
  }

  /*public static void main(String[] args) {
    //generarToken()
    JwtGenerator jwtGenerator = new JwtGenerator();
    String token = jwtGenerator.generarToken();
    System.out.println("JWT generado: " + token);

    //generarTokenConClaims()
    JwtGenerator jwtGeneratorCC = new JwtGenerator();
    String tokenCC = jwtGeneratorCC.generarTokenConClaims();
    System.out.println("JWT generado con claims: " + tokenCC);
  }*/
}