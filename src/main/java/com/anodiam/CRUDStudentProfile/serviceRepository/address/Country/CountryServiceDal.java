package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CountryServiceDal extends CountryServiceImpl {

    @Autowired
    private CountryRepository countryRepository;

    public CountryServiceDal(){}

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
