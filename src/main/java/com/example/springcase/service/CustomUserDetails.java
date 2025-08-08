package com.example.springcase.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springcase.model.User;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user;

    

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // şifre hashli olmalı
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // ya da user.getUsername(), sen hangisini kullanıyorsan
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // hesabın süresi dolmadı mı?
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // hesap kilitli mi?
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // kimlik bilgileri süresi doldu mu?
    }

    @Override
    public boolean isEnabled() {
        return true; // hesap aktif mi?
    }

    public User getUser() {
        return user;
    }
}
