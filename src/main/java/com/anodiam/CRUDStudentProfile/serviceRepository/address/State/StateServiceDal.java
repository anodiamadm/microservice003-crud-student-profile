package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class StateServiceDal extends StateServiceImpl {

    @Autowired
    private StateService stateService;

    public StateServiceDal(){}

    @Override
    public List<State> findByCountryId(BigInteger countryId) {
        return (List<State>) stateService.findByCountryId(countryId);
    }
}
