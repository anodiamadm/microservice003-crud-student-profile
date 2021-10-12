package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import java.util.List;

abstract class CountryServiceImpl implements CountryService {

    @Override
    public MessageResponse save(Country country){return new CountryServiceDal().save(country);}

    @Override
    public List<Country> findAll() {
        return new CountryServiceDal().findAll();
    }
}
