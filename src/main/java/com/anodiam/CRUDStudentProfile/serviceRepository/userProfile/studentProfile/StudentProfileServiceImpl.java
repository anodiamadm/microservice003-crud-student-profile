package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;

import java.math.BigInteger;
import java.util.Optional;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public StudentProfile save(StudentProfile studentProfile){
        return new StudentProfileServiceDal().save(studentProfile);
    }

    @Override
    public Optional<StudentProfile> findById(BigInteger studentProfileId){
        return new StudentProfileServiceDal().findById(studentProfileId);
    }
}
