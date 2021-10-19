package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.math.BigInteger;

public interface StudentProfileService {

    StudentProfile save(StudentProfile studentProfile);
    StudentProfile modify(StudentProfile studentProfile);
    MessageResponse removeOne(BigInteger studentProfileId);
}
