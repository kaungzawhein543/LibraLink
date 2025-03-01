package com.elibrary.LibraLink.security;


import com.elibrary.LibraLink.dtos.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

    // CONSTANT VALUES
    private final int cookieExpirationDate = 30 * 24 * 60 * 60;

    // ADD TOKEN TO COOKIES
    public void addTokenToCookie(String token, boolean rememberMe) {
        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        if(rememberMe) {
            cookie.setMaxAge(30 * 24 * 60 * 60);
        }
    }

    // REMOVE COOKIE (LOGOUT)
    public Cookie removeCookieToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("jwt".equals(cookie.getName())){
                  cookie.setMaxAge(0);
                  return cookie;
                }
            }
        }
        Cookie cookie = new Cookie("jwt", "");
        cookie.setMaxAge(0);
        return cookie;
    }

}
