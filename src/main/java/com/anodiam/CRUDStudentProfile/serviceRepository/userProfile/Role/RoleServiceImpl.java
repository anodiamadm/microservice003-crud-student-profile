package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Role;

import com.anodiam.CRUDStudentProfile.model.Role;

abstract class RoleServiceImpl implements RoleService {

    @Override
    public Role findByRoleName(String roleName) {
        return new RoleServiceDal().findByRoleName(roleName);
    }
}
