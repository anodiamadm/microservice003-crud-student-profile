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
import java.util.regex.Pattern;

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
            if(returnMessage.length() > 0)
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
            String returnMessage=ValidateBeforeSave(studentProfile,"modify");
            if(returnMessage.length() > 0)
            {
                studentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),returnMessage));
                return studentProfile;
            }
            StudentProfile studentProfileToModify= studentProfileRepository.findById(studentProfile.getStudent_profile_id()).get();
            if(studentProfileToModify!=null)
            {
                studentProfileToModify.setFullName(studentProfile.getFullName());
                studentProfileToModify.setPhoneNumber(studentProfile.getPhoneNumber());
                studentProfileToModify.setAddress(studentProfile.getAddress());
                studentProfileToModify.setLatitude(studentProfile.getLatitude());
                studentProfileToModify.setLongitude(studentProfile.getLongitude());
                studentProfileToModify.setGuardiansName(studentProfile.getGuardiansName());
                studentProfileToModify.setGuardiansEmail(studentProfile.getGuardiansEmail());
                studentProfileToModify.setGuardiansPhoneNumber(studentProfile.getGuardiansPhoneNumber());
                studentProfileToModify.setBoard(studentProfile.getBoard());
                studentProfileToModify.setLevel(studentProfile.getLevel());
                studentProfileToModify.setSuburb(studentProfile.getSuburb());
                studentProfileToModify.setProfileImageLink(studentProfile.getProfileImageLink());
                studentProfileToModify.setUser(studentProfile.getUser());
                studentProfileToModify.setLanguage(studentProfile.getLanguage());
                StudentProfile student = studentProfileRepository.save(studentProfileToModify);
                returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_MODIFY_SUCCESS");
                studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), returnMessage));
                return student;
            }
            returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_MODIFY_FAILURE");
            studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), returnMessage));
            return studentProfileToModify;
        } catch (Exception exception) {
            exception.printStackTrace();
            studentProfile.setMessageResponse(errorService.GetErrorMessage(exception.getMessage()));
            return null;
        }
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId)
    {
        String returnMessage="";
        MessageResponse messageResponse = new MessageResponse();
        try
        {
            returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_DELETE_SUCCESS");
            studentProfileRepository.deleteById(studentProfileId);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(), returnMessage);
        }catch(Exception exception)
        {
            exception.printStackTrace();
            returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_DELETE_FAILURE");
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),returnMessage);
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
            Optional<StudentProfile> optStudentProfile= studentProfileRepository.findByUser(studentProfile.getUser());
            if(optStudentProfile.isPresent())
            {
                return messageService.showMessage(language_Id,"STUDENT_USER_ID_PRESENT");
            }

        }
        if(methodName=="modify")
        {
            if(studentProfile.getStudent_profile_id().intValue()<=0)
            {
                return messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_INVALID");
            }
            Optional<StudentProfile> optStudentProfile= studentProfileRepository.findByUser(studentProfile.getUser());
            if(!optStudentProfile.isPresent())
            {
                return messageService.showMessage(language_Id,"STUDENT_USER_ID_NOT_PRESENT");
            }
        }

        if(studentProfile.getGuardiansEmail().trim().length() > 0 &&  !isValidEmail(studentProfile.getGuardiansEmail()))
        {
            return messageService.showMessage(language_Id,"GUARDIAN_INVALID_EMAIL_ADDRESS");
        }

        if(studentProfile.getLanguage() == null || studentProfile.getLanguage().getLanguage_id() == null)
        {
            return messageService.showMessage(language_Id,"STUDENT_LANGUAGE_ID_BLANK");
        }
        return "";
    }

    private static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
