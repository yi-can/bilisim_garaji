package com.example.springcase.request;

public class AuthenticationRequest {
    private String username;
    private String password;

    // Getter-setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
