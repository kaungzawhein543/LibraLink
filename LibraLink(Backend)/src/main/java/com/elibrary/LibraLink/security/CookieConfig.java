package com.elibrary.LibraLink.security;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieConfig {

    private final int cookieExpirationDate = 30 * 24 * 60 * 60;


    //ADD TOKEN TO COOKIES
    public Cookie addTokenToCookie(String token, boolean rememberMe) {
        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        if(rememberMe) {
            cookie.setMaxAge(30 * 24 * 60 * 60);
        }
        return cookie;
    }

    //REMOVE COOKIE (LOGOUT)
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
