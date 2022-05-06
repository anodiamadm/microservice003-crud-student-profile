package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.util.Optional;

public interface StudentProfileService {
    Optional<StudentProfile> findByUser(User user);
    MessageResponse deleteByUser(User user);
    MessageResponse save(StudentProfile studentProfile);
}
