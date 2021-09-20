package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
class StudentProfileServiceDal {

    @Autowired
    private StudentProfileService studentProfileService;

    public StudentProfile save(StudentProfile studentProfile) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            StudentProfile savedStudentProfile = studentProfileService.save(studentProfile);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                    "Successfully Saved Student Profile for ID: " + studentProfile.getStudentProfileId() + ": "
                            + studentProfile.getFirstName() + "!");
            savedStudentProfile.setMessageResponse(messageResponse);
            return savedStudentProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                    "Failed to Save Student Profile for ID: " + studentProfile.getStudentProfileId()
                            + ": " + studentProfile.getFirstName() + "!\n" + exception.getMessage());
            studentProfile.setMessageResponse(messageResponse);
            return studentProfile;
        }
    }

    public Optional<StudentProfile> findById(BigInteger studentProfileId) {

        try {
            Optional<StudentProfile> optionalStudentProfile =
                    studentProfileService.findById(studentProfileId);
            if(optionalStudentProfile.isPresent()) {
                return optionalStudentProfile;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
