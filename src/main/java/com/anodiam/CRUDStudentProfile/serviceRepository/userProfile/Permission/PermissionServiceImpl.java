package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission;

import com.anodiam.CRUDStudentProfile.model.Permission;

abstract class PermissionServiceImpl implements PermissionService {

    @Override
    public Permission findByPermissionName(String permissionName) {
        return new PermissionServiceDal().findByPermissionName(permissionName);
    }
}
