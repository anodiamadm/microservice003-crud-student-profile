package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;


import java.util.List;

public interface CountryService {
    MessageResponse save(Country country);
    List<Country> findAll();
}
