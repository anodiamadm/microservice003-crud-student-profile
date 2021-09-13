package com.anodiam.CRUDStudentProfile.db.repository.address;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CountryRepository extends JpaRepository<Country, BigInteger> {
}
