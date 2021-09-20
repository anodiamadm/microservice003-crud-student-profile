package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;

import java.math.BigInteger;
import java.util.Optional;

public interface StudentProfileService {
    Optional<StudentProfile> findById(BigInteger studentProfileId);
    StudentProfile save(StudentProfile studentProfile);
    StudentProfile modify(StudentProfile studentProfile);
}
