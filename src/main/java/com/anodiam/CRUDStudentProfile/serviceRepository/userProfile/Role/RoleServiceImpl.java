package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Role;

import com.anodiam.CRUDStudentProfile.model.Role;

import java.util.Optional;

abstract class RoleServiceImpl implements RoleService {

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return new RoleServiceDal().findByRoleName(roleName);
    }
}
