package com.anodiam.CRUDStudentProfile.serviceRepository.address.Town;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, BigInteger> {

    List<Town> findByState(State state);

    @Override
    Optional<Town> findById(BigInteger townId);
}
