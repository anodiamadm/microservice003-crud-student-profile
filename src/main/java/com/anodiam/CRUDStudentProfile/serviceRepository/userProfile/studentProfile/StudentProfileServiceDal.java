package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StudentProfileServiceDal {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public StudentProfile save(StudentProfile studentProfile) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                    "Successfully Saved Student Profile for ID: " + studentProfile.getStudentProfileId() + ": "
                            + studentProfile.getFirstName() + "!");
            savedStudentProfile.setReturnMessage(messageResponse);
            return savedStudentProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                    "Failed to Save Student Profile for ID: " + studentProfile.getStudentProfileId()
                            + ": " + studentProfile.getFirstName() + "!\n" + exception.getMessage());
            studentProfile.setReturnMessage(messageResponse);
            return studentProfile;
        }
    }
}
