package com.anodiam.CRUDStudentProfile.serviceRepository.errorHandling;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ErrorHandlingDal extends ErrorHandlingImpl {

    class ErrorHandling
    {
        Integer errorNumber;
        String errorKey;
        String errorMessage;

        public ErrorHandling(){}

        public ErrorHandling(Integer errorNumber,String errorKey,String errorMessage)
        {
            this.errorNumber=errorNumber;
            this.errorKey=errorKey;
            this.errorMessage=errorMessage;
        }
    }

    @Override
    public MessageResponse GetErrorMessage(String errorMessage)
    {
        System.out.println("Inside ErrorDAL: " + errorMessage);
        List<ErrorHandling> lstErrorHandle=populateErrorHandlers();
        for(int i=0;i<lstErrorHandle.size();i++)
        {
            ErrorHandling objErrorHandling=lstErrorHandle.get(i);
            if (errorMessage.contains(objErrorHandling.errorKey)) {
                return new MessageResponse(objErrorHandling.errorNumber,
                        objErrorHandling.errorMessage);
            }
        }
        return new MessageResponse(ResponseCode.FAILURE.getID(),
                "Failed to retrieve/update details");
    }

    private List<ErrorHandling> populateErrorHandlers()
    {
        List<ErrorHandling> lstErrorHandle=new ArrayList<ErrorHandling>();
        //Handling of Country Errors
        lstErrorHandle.add(new ErrorHandling(ResponseCode.DUPLICATE.getID(),
       "uk_country_code","Country code already exists"));

        lstErrorHandle.add(new ErrorHandling(ResponseCode.DUPLICATE.getID(),
                "uk_country_name","Country name already exists"));

        //Handling for Edit Mode. If User gives a primary key data which is not in database.
        lstErrorHandle.add(new ErrorHandling(ResponseCode.INVALID.getID(),
                "No value present","Existing Record not present to modify"));

        return lstErrorHandle;
    }
}
