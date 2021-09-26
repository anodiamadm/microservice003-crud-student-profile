package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.State;

import java.math.BigInteger;
import java.util.List;

abstract class StateServiceImpl implements StateService {

    @Override
    public List<State> findByCountry(BigInteger countryId) {
        return new StateServiceDal().findByCountry(countryId);
    }
}
