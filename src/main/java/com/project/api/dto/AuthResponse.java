package com.project.api.dto;

public class AuthResponse {
    
    private String jwt;
    private Long id;
    private String nome;
    private String email;

    public AuthResponse(String token, Long id, String nome, String email) {
        this.jwt = token;
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }
}