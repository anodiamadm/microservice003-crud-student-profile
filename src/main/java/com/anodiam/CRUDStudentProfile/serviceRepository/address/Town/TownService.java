package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.address.Town;

import java.math.BigInteger;
import java.util.List;

public interface TownService {
    List<Town> findByState(BigInteger stateId);
}
