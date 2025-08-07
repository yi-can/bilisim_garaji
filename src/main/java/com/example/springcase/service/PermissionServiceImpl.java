package com.example.springcase.service;

import com.example.springcase.model.Permission;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public boolean hasPermission(Role role, ResourceName resource, String action) {
        Optional<Permission> permission = permissionRepository.findByRoleAndResourceName(role, resource);
        if (permission.isEmpty()) return false;

        String act = action.toLowerCase();
        Permission perm = permission.get();

        if (act.equals("create")) return perm.isCanCreate();
        else if (act.equals("read")) return perm.isCanRead();
        else if (act.equals("update")) return perm.isCanUpdate();
        else if (act.equals("delete")) return perm.isCanDelete();
        else return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, ResourceName resource, String action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPermission'");
    }

}
