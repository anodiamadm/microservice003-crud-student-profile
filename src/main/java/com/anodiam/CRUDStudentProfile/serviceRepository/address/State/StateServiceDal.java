package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.address.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
class StateServiceDal extends StateServiceImpl {

    @Autowired
    private StateService stateService;

    public StateServiceDal(){}

    @Override
    public List<State> findByCountryId(BigInteger countryId) {

        try {
            List<State> states = stateService.findByCountryId(countryId);
            if(!states.isEmpty()) {
                return states;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
