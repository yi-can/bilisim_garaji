package com.example.springcase.service;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;

import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;  // Aynı paketten

public interface PermissionService {
    boolean hasPermission(Role role, ResourceName resource, String action);

    boolean hasPermission(Authentication authentication, ResourceName resource, String action);
}
