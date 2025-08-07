package com.example.springcase.service;

import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;  // Aynı paketten

public interface PermissionService {
    boolean hasPermission(Role role, ResourceName resource, String action);
}
