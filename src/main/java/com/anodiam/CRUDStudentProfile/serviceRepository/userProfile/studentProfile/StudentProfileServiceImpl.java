package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;

abstract class StudentProfileServiceImpl implements StudentProfileService {

    @Override
    public StudentProfile save(StudentProfile studentProfile) {

        return new StudentProfileServiceDal().save(studentProfile);
    }
}
