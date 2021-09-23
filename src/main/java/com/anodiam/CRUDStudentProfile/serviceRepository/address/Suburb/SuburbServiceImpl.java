package com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb;

import com.anodiam.CRUDStudentProfile.model.address.Suburb;

import java.math.BigInteger;
import java.util.List;

abstract class SuburbServiceImpl implements SuburbService {

    @Override
    public List<Suburb> findByTown(BigInteger townId) {
        return new SuburbServiceDal().findByTown(townId);
    }
}
