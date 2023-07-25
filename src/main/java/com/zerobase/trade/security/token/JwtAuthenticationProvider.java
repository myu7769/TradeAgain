package com.zerobase.trade.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;


public class JwtAuthenticationProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;
    private long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String account, Long id) {

        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(account)).setId(Aes256Util.encrypt(id.toString()));
        Date now = new Date();

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return "Bearer " + jwtToken;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

/*    public UserVo getUserVo(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserVo(Long.valueOf(Aes256Util.decrypt(claims.getId())), Aes256Util.decrypt(claims.getSubject()));

    }*/
}
