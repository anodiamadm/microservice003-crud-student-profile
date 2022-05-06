package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission;

import com.anodiam.CRUDStudentProfile.model.Permission;

import java.util.Optional;

public interface PermissionService {
    Optional<Permission> findByPermissionName(String permissionName);
}
