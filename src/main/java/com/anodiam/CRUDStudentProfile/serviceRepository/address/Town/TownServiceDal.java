package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.State.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class TownServiceDal extends TownServiceImpl {

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private StateRepository stateRepository;

    public TownServiceDal(){}

    @Override
    public List<Town> findByState(BigInteger stateId) {
        try {
            State state = stateRepository.getById(stateId);
            List<Town> towns = townRepository.findByState(state);
            if(!towns.isEmpty()) {
                return towns;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
