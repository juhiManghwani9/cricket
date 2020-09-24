package com.cricket.packages.exception;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {




    //Changing response header to block pop up in UI in case wrong credential

    public void commence(HttpServletRequest request, HttpServletResponse response,
                  org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException{

        response.setHeader("WWW-Authenticate", "FormBased");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}