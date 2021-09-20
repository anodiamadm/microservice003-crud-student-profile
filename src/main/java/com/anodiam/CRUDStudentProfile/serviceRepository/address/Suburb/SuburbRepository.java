package com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb;

import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface SuburbRepository extends JpaRepository<Suburb, BigInteger> {

    List<Suburb> findByTown(Town town);
}
