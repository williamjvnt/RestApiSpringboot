package com.example.RestApi.Service;

import com.example.RestApi.Repository.BlackListRepository;
import com.example.RestApi.entity.Blacklist;
import com.example.RestApi.entity.CustomerEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class JwtService {

    @Autowired
    BlackListRepository blackListRepository;
    //The methods that will be used are generateToken(), isTokenValid() and getExpirationTime()

    //value ini dan jwtexpiration diambil dari applicaton.properties
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;



    //melakukan extract username didalam string menggunakan claims
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(String nama) {
        return generateToken(new HashMap<>(), nama);
    }

    public String generateToken(Map<String, Object> extraClaims, String nama) {
        return buildToken(extraClaims, nama, jwtExpiration);
    }
    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            String nama,
            long expiration
    ) {
        log.info("cihuy :"+ getSignInKey());
        log.info(secretKey);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(nama)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public List<Blacklist> getAllSistemHitam(){
        return blackListRepository.findAll();
    }
    public boolean isTokenValid(String token, CustomerEntity customer) {
        final String username = extractUsername(token);
        if(blackListRepository.existsById(token)){
            return false;
        }
        return (username.equals(customer.getNama())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
