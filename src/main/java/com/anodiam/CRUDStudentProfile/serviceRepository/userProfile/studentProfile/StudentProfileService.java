package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.math.BigInteger;
import java.util.Optional;

public interface StudentProfileService {

    Optional<StudentProfile> findByUser(User user);
    StudentProfile save(StudentProfile studentProfile);
    StudentProfile modify(StudentProfile studentProfile);
    MessageResponse removeOne(BigInteger studentProfileId);
}
