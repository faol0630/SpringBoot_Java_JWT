package com.faol.JWT.security.jwt;


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

    private static final String SECRET_KEY = "IFJ2987492NDSLKDSFLA27284944NDDNFD393930LKJSAD8UASD";

    public String getToken(UserDetails userDetails){
        return getToken(new HashMap<>(),  userDetails);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .signWith( getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //hasta esta parte es la implementacion que se hace en la primera parte que tiene que
    //ver con login.
    //-----------------------------------------------------------------------------------

    //estos metodos se implementan al final para poder tener acceso a endpoints protegidos:
    //2)
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //6)
    public boolean isTokenvalid(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);
        //si son iguales y si el token no ha expirado.Poner signo de admiracion:
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //devuelve true o false
    }

    //1) creamos este metodo primero
    //se llama desde getUsernameFromToken(String token) y getExpiration(String token)
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //metodos para usar con isTokenValid()
    //metodo para usar con getClaim():
    //4)
    private Claims getAllClaims(String token ){

        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //3)
    private Date getExpiration(String token){

        return getClaim(token, Claims::getExpiration);
    }

    //metodo que sera llamado desde isTokenValid()
    //5)
    private boolean isTokenExpired(String token){

        return getExpiration(token).before(new Date());
    }
}
