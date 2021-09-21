package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;

import java.math.BigInteger;
import java.util.Optional;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public Optional<StudentProfile> findById(BigInteger studentProfileId){

        return new StudentProfileServiceDal().findById(studentProfileId);
    }

    @Override
    public StudentProfile save(StudentProfile studentProfile, User user){
        return new StudentProfileServiceDal().save(studentProfile, user);
    }

    @Override
    public StudentProfile modify(StudentProfile studentProfile, User user){
        return new StudentProfileServiceDal().modify(studentProfile, user);
    }
}
