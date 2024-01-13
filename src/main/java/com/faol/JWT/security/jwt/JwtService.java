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
    //Up to here is the implementation that is done in the first part
    //-----------------------------------------------------------------------------------

    //These methods are implemented at the end to be able to access protected endpoints:
    //2)
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //6)
    public boolean isTokenvalid(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);
        //if they are equal and if the token has not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //return true o false
    }

    //1) create this method first
    //method called from getUsernameFromToken(String token) and getExpiration(String token)
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //methods to use with isTokenValid()
    //method to use with getClaim():
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

    //method that will be called from isTokenValid()
    //5)
    private boolean isTokenExpired(String token){

        return getExpiration(token).before(new Date());
    }
}
