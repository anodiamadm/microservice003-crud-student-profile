package com.anodiam.CRUDStudentProfile.db.repository.address;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, BigInteger> {

    List<Town> findByState(BigInteger stateId);

}
