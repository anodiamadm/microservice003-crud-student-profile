package com.anodiam.CRUDStudentProfile.serviceRepository.address.State;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, BigInteger> {


    List<State> findByCountry(Country country);

    @Override
    Optional<State> findById(BigInteger stateId);
}
