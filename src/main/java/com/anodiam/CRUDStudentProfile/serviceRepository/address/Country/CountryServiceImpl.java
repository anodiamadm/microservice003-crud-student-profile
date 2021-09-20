package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;

import java.util.List;

abstract class CountryServiceImpl implements CountryService{

    @Override
    public List<Country> findAll() { return new CountryServiceDal().findAll(); }
}
