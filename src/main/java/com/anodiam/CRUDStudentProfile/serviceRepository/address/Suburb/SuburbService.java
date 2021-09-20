package com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb;

import com.anodiam.CRUDStudentProfile.model.address.Suburb;

import java.math.BigInteger;
import java.util.List;

public interface SuburbService {
    List<Suburb> findByTownId(BigInteger townId);
}
