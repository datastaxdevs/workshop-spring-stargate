package com.datastax.astraportia.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Utility class to work with Cookie and Session Attributes
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class WebUtils {

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null ? session.getId() : null);
    }

    public static Object getSessionAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        return (session != null ? session.getAttribute(name) : null);
    }
 
    public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        if (value != null) {
            request.getSession().setAttribute(name, value);
        }
        else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(name);
            }
        }
    }
    
}
