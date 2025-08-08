package com.example.springcase.controller;

import com.example.springcase.model.User;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.security.CustomUserDetails;
import com.example.springcase.service.PermissionService;
import com.example.springcase.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.USER, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu işlemi yapma yetkiniz yok");
        }

        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.USER, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yetkisiz erişim");
        }

        User foundUser = userService.findById(id);
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User newUser, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.USER, "create")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Kullanıcı oluşturma yetkiniz yok");
        }

        User createdUser = userService.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody User updatedUser, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.USER, "update")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Kullanıcı güncelleme yetkiniz yok");
        }

        User updated = userService.update(id, updatedUser);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.USER, "delete")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Kullanıcı silme yetkiniz yok");
        }

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
