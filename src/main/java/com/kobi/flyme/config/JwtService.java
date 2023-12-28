//package com.kobi.flyme.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    @Value("${jwt.secret.key}")
//    private static String SECRET_KEY;
//    public String extractUsername(String jwt){
//        return extractClaim(jwt, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
//        final Claims claims = extractAllClaims(jwt);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts // from io.json.webtoken
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignInKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails user){
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60)) // 1hour
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // pick algo
//                .compact(); // generates
//    }
//
//    public String generateToken(UserDetails user){
//        return generateToken(new HashMap<>(), user);
//    }
//
//
//    public boolean isTokenValid(String token, UserDetails user){
//        final String username = extractUsername(token);
//        return (username == user.getUsername()) && !isTokenExpired(token);
//    }
//
//    public boolean isTokenExpired(String token){
//        return extractClaim(token, Claims::getExpiration).before(new Date());
//    }
//
//}
