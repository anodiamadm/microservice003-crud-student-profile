package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LevelService {
    Optional<Level> findById(BigInteger levelId);
}
