package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.util.Optional;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public Optional<StudentProfile> findByUser(User user){
        return new StudentProfileServiceDal().findByUser(user);
    }

    @Override
    public MessageResponse deleteByUser(User user) {
        return new StudentProfileServiceDal().deleteByUser(user);
    }

    @Override
    public MessageResponse save(StudentProfile studentProfile) {
        return new StudentProfileServiceDal().save(studentProfile);
    }
}
