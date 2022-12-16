package main.systems.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.systems.security.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtService {

//    private final String secret = "Hjwjv5vvNX1v8se2AVPeibK6s0c89kFtNwsEvNWreguz6kVMf92ZkjSFp8rmA0jt";

    @Autowired
    private JwtProperties properties;

    public String generateJwtToken(UserDetails user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }


    public String getUsername(String bearerTokenValue) {
        return parse(bearerTokenValue).getSubject();
    }

    public List<GrantedAuthority> getAuthority(String bearerTokenValue) {
        List<String> authority = (List<String>) parse(bearerTokenValue).get("authority");

        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }

}
