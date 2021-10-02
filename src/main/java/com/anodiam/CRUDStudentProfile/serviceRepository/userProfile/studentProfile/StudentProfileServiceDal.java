package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public StudentProfileServiceDal(){}

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        try {
            StudentProfile studentProfileToSave = studentProfileRepository.save(studentProfile);
            studentProfileToSave.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), "Student Profile Saved Successfully!"));
            return studentProfileToSave;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), exception.getMessage()));
            return studentProfile;
        }
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId)
    {
        MessageResponse messageResponse = new MessageResponse();
        try
        {
            studentProfileRepository.deleteById(studentProfileId);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(), "Student Profile deleted successfully!");
        }catch(Exception exception)
        {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),"Failed to delete Student Profile");
        }
        return messageResponse;
    }
}
