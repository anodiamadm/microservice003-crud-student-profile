package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.CRUDStudentProfileApplication;
import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.Message.MessageService;
import com.anodiam.CRUDStudentProfile.serviceRepository.errorHandling.ErrorHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private ErrorHandlingService errorService;

    @Autowired
    private MessageService messageService;

    public StudentProfileServiceDal(){}

    int language_Id= CRUDStudentProfileApplication.languageId;

    @Override
    public Optional<StudentProfile> findByUser(User user)
    {
        Optional<StudentProfile> optionalStudentProfile;
        try
        {
            optionalStudentProfile = studentProfileRepository.findByUser(user);
        }catch(Exception exception)
        {
            exception.printStackTrace();
            StudentProfile errorStudentProfile = new StudentProfile();
            errorStudentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), "Student Profile could not be retrieved!"));
            optionalStudentProfile = Optional.of(errorStudentProfile);
        }
        return optionalStudentProfile;
    }


    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        try
        {
            String returnMessage=ValidateBeforeSave(studentProfile,"save");
            if(returnMessage.length()==0)
            {
                studentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),returnMessage));
                return studentProfile;
            }
            StudentProfile studentProfileToSave = studentProfileRepository.save(studentProfile);
            returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_SAVE_SUCCESS");
            studentProfileToSave.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),returnMessage));
            return studentProfileToSave;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(errorService.GetErrorMessage(exception.getMessage()));
            return studentProfile;
        }
    }

    @Override
    public StudentProfile modify(StudentProfile studentProfile) {
        try
        {
            StudentProfile studentProfileToModify= studentProfileRepository.findById(studentProfile.getStudent_profile_id()).get();
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
                studentProfileToModify.setLanguage(studentProfile.getLanguage());
                StudentProfile student = studentProfileRepository.save(studentProfileToModify);
                studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), "Student Profile Saved Successfully!"));
                return student;
            }
            studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), "Student Profile Failed to Modify!"));
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(errorService.GetErrorMessage(exception.getMessage()));
            return null;
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

    private String ValidateBeforeSave(StudentProfile studentProfile,String methodName)
    {
        if(methodName=="save")
        {
            if(studentProfile.getStudent_profile_id().intValue()!=0)
            {
                return messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_BLANK");
            }
            if(studentProfile.getFirstName().trim().length()==0)
            {
                return messageService.showMessage(language_Id,"STUDENT_FIRST_NAME_BLANK");
            }
            if(studentProfile.getLastName().trim().length()==0)
            {
                return messageService.showMessage(language_Id,"STUDENT_LAST_NAME_BLANK");
            }
        }
        return "";
    }
}
