package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.common.ErrorHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private ErrorHandlingService errorService;

    public StudentProfileServiceDal(){}

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        try {
            StudentProfile studentProfileToSave = studentProfileRepository.save(studentProfile);
            studentProfileToSave.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), "Student Profile Saved Successfully!"));
            return studentProfileToSave;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(errorService.GetErrorMessage(exception.getMessage()));
            return studentProfile;
        }
    }

    /*@Override
    public MessageResponse modify(Designation designation)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Designation designationToModify= desigRep.findById(designation.getDesignation_id()).get();
            if(designationToModify!=null)
            {
                designationToModify.setDesignation_name(designation.getDesignation_name());
                desigRep.save(designationToModify);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Designation details modified successfully!");
                return msgResp;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Designation details not found to modify");
            return msgResp;
        }catch(Exception ex)
        {
            return errorService.GetErrorMessage(ex.getMessage());
        }
    }*/

    @Override
    public StudentProfile modify(StudentProfile studentProfile) {
        try
        {
            StudentProfile studentProfileToModify= studentProfileRepository.findById(studentProfile.getStudentProfileId()).get();
            if(studentProfileToModify!=null)
            {
                studentProfileToModify.setFirstName(studentProfile.getFirstName());
                studentProfileToModify.setMiddleName(studentProfile.getMiddleName());
                studentProfileToModify.setLastName(studentProfile.getLastName());
                studentProfileToModify.setEmail(studentProfile.getEmail());
                studentProfileToModify.setPhoneNumber(studentProfile.getPhoneNumber());
                studentProfileToModify.setGuardiansFirstName(studentProfile.getGuardiansFirstName());
                studentProfileToModify.setGuardiansLastName(studentProfile.getGuardiansLastName());
                studentProfileToModify.setGuardiansEmail(studentProfile.getGuardiansEmail());
                studentProfileToModify.setGuardiansPhoneNumber(studentProfile.getGuardiansPhoneNumber());
                studentProfileToModify.setBoard(studentProfile.getBoard());
                studentProfileToModify.setLevel(studentProfile.getLevel());
                studentProfileToModify.setSuburb(studentProfile.getSuburb());
                studentProfileToModify.setProfileImageLink(studentProfile.getProfileImageLink());
                studentProfileToModify.setUser(studentProfile.getUser());

                studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), "Student Profile Saved Successfully!"));
                return studentProfileToModify;
            }
            studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), "Student Profile Failed to Modify!"));
            return studentProfileToModify;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(errorService.GetErrorMessage(exception.getMessage()));
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
