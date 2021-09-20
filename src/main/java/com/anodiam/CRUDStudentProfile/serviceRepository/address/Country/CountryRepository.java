package com.anodiam.CRUDStudentProfile.serviceRepository.address.Country;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CountryRepository extends JpaRepository<Country, BigInteger> {
}
