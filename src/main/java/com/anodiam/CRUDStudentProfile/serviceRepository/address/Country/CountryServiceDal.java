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

    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
}
