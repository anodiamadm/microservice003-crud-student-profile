package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.address.Town;

import java.math.BigInteger;
import java.util.List;

abstract class TownServiceImpl implements TownService {

    @Override
    public List<Town> findByState(BigInteger stateId) {
        return new TownServiceDal().findByState(stateId);
    }
}
