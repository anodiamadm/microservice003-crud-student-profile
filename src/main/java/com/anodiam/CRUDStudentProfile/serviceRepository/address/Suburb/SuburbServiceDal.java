package com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb;

import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Town.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class SuburbServiceDal extends SuburbServiceImpl {

    @Autowired
    private SuburbRepository suburbRepository;

    @Autowired
    private TownRepository townRepository;

    public SuburbServiceDal(){}

    @Override
    public List<Suburb> findByTown(BigInteger townId) {
        try {
            Town town = townRepository.getById(townId);
            List<Suburb> suburbs = suburbRepository.findByTown(town);
            if(!suburbs.isEmpty()) {
                return suburbs;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
