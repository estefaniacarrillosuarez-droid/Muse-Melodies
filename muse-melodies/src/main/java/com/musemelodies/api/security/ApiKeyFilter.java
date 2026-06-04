package com.musemelodies.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component permite que Spring detecte automáticamente este filtro.
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    // Obtiene la clave configurada en application.properties.
    @Value("${api.key.secret}")
    private String apiKeySecret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();

        // Permite las peticiones GET sin necesidad de autenticación.
        if ("GET".equalsIgnoreCase(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene la API Key enviada en la cabecera de la petición.
        String apiKey = request.getHeader("X-API-KEY");

        // Comprueba que la clave exista y coincida con la configurada.
        if (apiKey == null || !apiKey.equals(apiKeySecret)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"API Key invalida o faltante\"}");
            return;
        }

        // Si la clave es correcta, permite continuar la petición.
        filterChain.doFilter(request, response);
    }
}