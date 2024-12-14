package com.example.demo.service.dto.security;

import lombok.Data;

@Data
public class AuthRespuestaDTO {

    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthRespuestaDTO(String accessToken){
        this.accessToken = accessToken;
    }
    
}
