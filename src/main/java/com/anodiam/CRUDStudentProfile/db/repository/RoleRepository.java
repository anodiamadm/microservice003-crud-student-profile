package com.anodiam.CRUDStudentProfile.db.repository;

import com.anodiam.CRUDStudentProfile.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository<Role, BigInteger> {
}
