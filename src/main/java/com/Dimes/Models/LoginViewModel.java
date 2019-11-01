package com.Dimes.Models;

import java.util.Optional;

public class LoginViewModel  {

    private String username;
    private Optional<Integer> id;
    private String role;

    public LoginViewModel() {
    }

    public LoginViewModel(String username, Optional<Integer> id, String role) {
        this.username = username;
        this.id = id;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(Optional<Integer> id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
