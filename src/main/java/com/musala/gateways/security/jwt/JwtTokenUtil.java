package com.musala.gateways.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;


    /**
     * Tomado de Stackoverflow
     * <a href="https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string/40274325#40274325">...</a>
     * creates a spec-compliant secure-random key:
     */
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS512
    String base64Key = Encoders.BASE64.encode(key.getEncoded());

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        //test
        System.out.println(jwtExpirationMs);

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key) //nuevo
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();//nuevo
    }

    public boolean validateJwtToken(String authToken) {
        try {

            //test
            System.out.println("authToken JwtTokenUtil: "+authToken);
            System.out.println("key JwtTokenUtil: "+base64Key);

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);//nuevo
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e){
            log.error("Invalid JWT signature: {}", e.getMessage());
        }

        return false;
    }
}
