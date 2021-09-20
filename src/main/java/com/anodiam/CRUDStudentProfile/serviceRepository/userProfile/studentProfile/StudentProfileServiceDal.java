package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileService studentProfileService;

    @Override
    public Optional<StudentProfile> findById(BigInteger studentProfileId) {
        return studentProfileService.findById(studentProfileId);
    }

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            StudentProfile savedStudentProfile = studentProfileService.save(studentProfile);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                    "Successfully Saved Student Profile!");
            savedStudentProfile.setMessageResponse(messageResponse);
            return savedStudentProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                    "Failed to Save Student Profile!");
            StudentProfile failedToSaveProfile = new StudentProfile();
            failedToSaveProfile.setMessageResponse(messageResponse);
            return failedToSaveProfile;
        }
    }

    @Override
    public StudentProfile modify(StudentProfile studentProfile) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            if(studentProfileService.findById(studentProfile.getStudentProfileId())!=null) {
                StudentProfile savedStudentProfile = studentProfileService.save(studentProfile);
                messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                        "Successfully Modified Student Profile!");
                savedStudentProfile.setMessageResponse(messageResponse);
                return savedStudentProfile;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                "Failed to Modify Student Profile!");
        StudentProfile failedToSaveProfile = new StudentProfile();
        failedToSaveProfile.setMessageResponse(messageResponse);
        return failedToSaveProfile;
    }
}
