package com.anodiam.CRUDStudentProfile.db.repository.address;

import com.anodiam.CRUDStudentProfile.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, BigInteger> {

    List<State> findByCountryId(BigInteger countryId);
}
