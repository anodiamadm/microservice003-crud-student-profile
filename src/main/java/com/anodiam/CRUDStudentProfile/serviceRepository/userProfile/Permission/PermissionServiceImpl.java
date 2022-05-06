package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission;

import com.anodiam.CRUDStudentProfile.model.Permission;

import java.util.Optional;

abstract class PermissionServiceImpl implements PermissionService {

    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
        return new PermissionServiceDal().findByPermissionName(permissionName);
    }
}
