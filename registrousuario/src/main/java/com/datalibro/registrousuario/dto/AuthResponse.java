package com.datalibro.registrousuario.dto;

public class AuthResponse {

    public String token;

    public AuthResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
