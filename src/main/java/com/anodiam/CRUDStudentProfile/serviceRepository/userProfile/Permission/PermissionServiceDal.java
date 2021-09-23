package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission;

import com.anodiam.CRUDStudentProfile.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PermissionServiceDal extends PermissionServiceImpl {

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionServiceDal(){}

    @Override
    public Permission findByPermissionName(String permissionName) {
        try {
            Permission permission = permissionRepository.findByPermissionName(permissionName);
            return permission;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
