package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.math.BigInteger;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        return new StudentProfileServiceDal().save(studentProfile);
    }

    @Override
    public StudentProfile modify(StudentProfile studentProfile) {
        return new StudentProfileServiceDal().modify(studentProfile);
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId) {
        return new StudentProfileServiceDal().removeOne(studentProfileId);
    }
}
