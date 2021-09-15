package com.anodiam.CRUDStudentProfile.db.repository;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, BigInteger> {

    StudentProfile findByStudentProfileId(BigInteger studentProfileId);
}
