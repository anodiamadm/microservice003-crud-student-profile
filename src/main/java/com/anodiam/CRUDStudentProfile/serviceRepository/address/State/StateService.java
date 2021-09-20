package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.State;

import java.math.BigInteger;
import java.util.List;

public interface StateService {
    List<State> findByCountryId(BigInteger countryId);
}
