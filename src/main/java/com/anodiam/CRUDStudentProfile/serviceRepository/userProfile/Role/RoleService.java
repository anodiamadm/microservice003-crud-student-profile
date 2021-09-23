package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Role;

import com.anodiam.CRUDStudentProfile.model.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
