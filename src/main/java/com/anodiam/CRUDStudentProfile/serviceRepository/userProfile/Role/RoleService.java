package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Role;

import com.anodiam.CRUDStudentProfile.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleName(String roleName);
}
