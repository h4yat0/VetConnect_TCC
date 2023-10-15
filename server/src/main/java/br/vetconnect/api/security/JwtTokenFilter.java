package br.vetconnect.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ExceptionResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Date;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

            if(token != null && tokenProvider.validateToken(token)){
                Authentication auth = tokenProvider.getAuthentication(token);
                if(auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);

                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            e.printStackTrace();

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            if(e.getMessage().contains("The Token has expired")){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }else{
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }


            ExceptionResponse errorResponse = new ExceptionResponse(new Date(), HttpStatus.UNAUTHORIZED, e.getMessage());
            String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        }

    }
}
