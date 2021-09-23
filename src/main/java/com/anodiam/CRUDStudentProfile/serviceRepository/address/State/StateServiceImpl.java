package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryRepository;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

abstract class StateServiceImpl implements StateService {

    @Override
    public List<State> findByCountry(BigInteger countryId) {
        return new StateServiceDal().findByCountry(countryId);
    }
}
