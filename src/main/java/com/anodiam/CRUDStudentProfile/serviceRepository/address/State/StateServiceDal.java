package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class StateServiceDal extends StateServiceImpl {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public StateServiceDal(){}

    @Override
    public List<State> findByCountry(BigInteger countryId) {
        try {
            Country country = countryRepository.getById(countryId);
            List<State> states = stateRepository.findByCountry(country);
            if(!states.isEmpty()) {
                return states;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
