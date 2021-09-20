package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TownServiceDal extends TownServiceImpl {

    @Autowired
    private TownService townService;

    public TownServiceDal(){}

    @Override
    public List<Town> findByStateId(BigInteger stateId) {
        return (List<Town>) townService.findByStateId(stateId);
    }
}
