package com.anodiam.CRUDStudentProfile.db.repository.masterData;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LevelRepository extends JpaRepository<Level, BigInteger> {
}
