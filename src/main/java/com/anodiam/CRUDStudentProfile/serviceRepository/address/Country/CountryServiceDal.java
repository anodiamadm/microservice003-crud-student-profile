package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.common.ErrorHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CountryServiceDal extends CountryServiceImpl {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ErrorHandlingService errorService;

    public CountryServiceDal(){}

    public MessageResponse save(Country country){
        MessageResponse msgResp =new MessageResponse();
        try
        {
            Country countryToAdd = countryRepository.save(country);
            msgResp = new MessageResponse(ResponseCode.SUCCESS.getID(),
                    "Country details added successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            return errorService.GetErrorMessage(ex.getMessage());
        }
    }

    @Override
    public List<Country> findAll() {
        try {
            List<Country> countries = countryRepository.findAll();
            if(!countries.isEmpty()) {
                return countries;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
