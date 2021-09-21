package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class TownServiceDal extends TownServiceImpl {

    @Autowired
    private TownService townService;

    public TownServiceDal(){}

    @Override
    public List<Town> findByStateId(BigInteger stateId) {
        try {
            List<Town> towns = townService.findByStateId(stateId);
            if(!towns.isEmpty()) {
                return towns;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
