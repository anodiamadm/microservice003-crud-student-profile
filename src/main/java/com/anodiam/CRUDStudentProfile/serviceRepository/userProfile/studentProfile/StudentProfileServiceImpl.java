package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.math.BigInteger;
import java.util.Optional;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public Optional<StudentProfile> findByUser(User user){
        return new StudentProfileServiceDal().findByUser(user);
    }

    @Override
    public MessageResponse save(StudentProfile studentProfile) {
        return new StudentProfileServiceDal().save(studentProfile);
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId) {
        return new StudentProfileServiceDal().removeOne(studentProfileId);
    }

    @Override
    public Optional<StudentProfile> findById(BigInteger studentProfileId) {
        return new StudentProfileServiceDal().findById(studentProfileId);
    }
}
