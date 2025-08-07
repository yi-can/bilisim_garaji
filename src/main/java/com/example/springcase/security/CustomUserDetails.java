package com.example.springcase.security;

import com.example.springcase.model.User;
import com.example.springcase.model.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Kullanıcının rolünü GrantedAuthority olarak dönüyoruz.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Role enum'u isim olarak döndürülüyor
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Hesap aktif mi? Senin entity'de enabled var.
    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    // İstersen orijinal User objesine erişim metodu ekle
    public User getUser() {
        return user;
    }
}
