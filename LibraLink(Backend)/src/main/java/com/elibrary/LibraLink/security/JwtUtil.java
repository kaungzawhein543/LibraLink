package com.elibrary.LibraLink.security;

import com.elibrary.LibraLink.entities.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

     final String jwt_secret_key = "${spring.jwt.secret.key}";
     final String jwt_access_secret_key = "${spring.jwt.access.token.secret.key}";
     final String jwt_refresh_secret_key = "${spring.jwt.refresh.token.secret.key}";


     //GENERATE JWT TOKEN
    public String generate_jwt_token(User user) {
        Key jwtSecretKey = new SecretKeySpec(jwt_secret_key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id",user.getId())
                .claim("role",user.getRole_id())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24000 + 60 + 60))
                .signWith(jwtSecretKey)
                .compact();
    }

    //EXTRACT SUBJECT(USERNAME) FROM TOKEN
    public String extractUsername(String token) {

        String decryptedToken = decryptAccessToken(token);
        return Jwts.parserBuilder()
                .setSigningKey(jwt_secret_key.getBytes())
                .build()
                .parseClaimsJws(decryptedToken)
                .getBody()
                .getSubject();
    }

    //ENCRYPT THE ACCESS TOKEN AND RETURN ENCODED TOKEN
    public String encryptAccessToken(String token) {
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(jwt_access_secret_key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(token.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch (Exception e) {
            throw new RuntimeException("Error while encrypting JWT");
        }
    }

    //DECRYPT THE ACCESS TOKEN AND RETURN DECODED TOKEN
    public String decryptAccessToken(String token) {
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(jwt_access_secret_key.getBytes(),"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(token);

            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException("Error while decrypting JWT");
        }
    }

    //ENCRYPT THE REFRESH TOKEN AND RETURN ENCODED TOKEN
    public String encryptRefreshToken(String token) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(jwt_refresh_secret_key.getBytes(),"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

            byte[] encryptedBytes =  cipher.doFinal(token.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch(Exception e) {
            throw new RuntimeException("Error while encrypting JWT");
        }
    }

    //DECRYPT THE REFRESH TOKEN AND RETURN DECODED TOKEN
    public String decryptRefreshToken(String token) {
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(jwt_refresh_secret_key.getBytes(),"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(token);

            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException("Error while decrypting JWT");
        }
    }

    //GET USER DETAIL FROM TOKEN IN COOKIE
    public User getUserDetailFromToken(String token) {
        User user = new User();
        String decryptedToken = decryptAccessToken(token);
        Key jwtSecretKey = new SecretKeySpec(jwt_secret_key.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return user;
    }



}