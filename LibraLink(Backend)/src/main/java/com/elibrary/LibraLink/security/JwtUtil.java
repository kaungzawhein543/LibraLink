package com.elibrary.LibraLink.security;

import com.elibrary.LibraLink.entities.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

     @Value("${spring.jwt.secret.key}")
     private String jwt_secret_key;
     @Value("${spring.jwt.access.token.secret.key}")
     private String jwt_access_secret_key;
     @Value("${spring.jwt.refresh.token.secret.key}")
     private String jwt_refresh_secret_key;

     //GENERATE JWT TOKEN
    public String generate_jwt_token(User user,String token_type) {

        // ACCESS TOKEN TIME
        long threeDaysInMillis = 3 * 24 * 60 * 60 * 1000;

        // REFRESH TOKEN TIME
        long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000;

        byte[] secretBytes = jwt_secret_key.getBytes();
        Key signingKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id",user.getId())
                .claim("role",user.getRole_id())
                .setIssuedAt(new Date())
                .setExpiration(token_type.equalsIgnoreCase("ACCESS") ? new Date(System.currentTimeMillis() + threeDaysInMillis)
                        : new Date(System.currentTimeMillis() + sevenDaysInMillis))
                .signWith(signingKey)
                .compact();
    }

    // EXTRACT SUBJECT(USERNAME) FROM TOKEN
    public String extractUsername(String token) {

        String decryptedToken = decryptAccessToken(token);
        return Jwts.parserBuilder()
                .setSigningKey(jwt_secret_key.getBytes())
                .build()
                .parseClaimsJws(decryptedToken)
                .getBody()
                .getSubject();
    }

    // ENCRYPT THE ACCESS TOKEN AND RETURN ENCODED TOKEN
    public String encryptAccessToken(String token) {
        try{
            byte[] decodedKey = Base64.getDecoder().decode(jwt_access_secret_key);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(token.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch (Exception e) {
            throw new RuntimeException("Error while encrypting JWT Access Token" + e.getMessage());
        }
    }

    // DECRYPT THE ACCESS TOKEN AND RETURN DECODED TOKEN
    public String decryptAccessToken(String token) {
        try{
            byte[] decodedKey = Base64.getDecoder().decode(jwt_access_secret_key);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey,"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(token);

            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException("Error while decrypting JWT Access Token" + e.getMessage());
        }
    }

    // ENCRYPT THE REFRESH TOKEN AND RETURN ENCODED TOKEN
    public String encryptRefreshToken(String token) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(jwt_refresh_secret_key);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey,"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

            byte[] encryptedBytes =  cipher.doFinal(token.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch(Exception e) {
            throw new RuntimeException("Error while encrypting JWT Refresh Token" + e.getMessage());
        }
    }

    // DECRYPT THE REFRESH TOKEN AND RETURN DECODED TOKEN
    public String decryptRefreshToken(String token) {
        try{
            byte[] decodedKey = Base64.getDecoder().decode(jwt_refresh_secret_key);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey,"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(token);

            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException("Error while decrypting JWT Refresh Token" + e.getMessage());
        }
    }

    // GET USER DETAIL FROM TOKEN IN COOKIE
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

    // VALIDATE THE JWT TOKEN
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // VALIDATE THE TOKEN
    private boolean isTokenExpired(String token) {
        String decryptedToken = decryptAccessToken(token);
        return Jwts.parser()
                .setSigningKey(jwt_secret_key.getBytes())
                .parseClaimsJws(decryptedToken)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}