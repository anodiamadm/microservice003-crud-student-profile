package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission;

import com.anodiam.CRUDStudentProfile.model.Permission;

public interface PermissionService {
    Permission findByPermissionName(String permissionName);
}
