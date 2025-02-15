package com.felipevilla.workhub.model.dto;

public class AuthenticationRequest {

    private String email;
    private String password;

    // Constructor por defecto necesario para Jackson
    public AuthenticationRequest() {
    }

    // Constructor con parámetros para facilitar pruebas
    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

