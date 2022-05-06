package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

abstract class LevelServiceImpl implements LevelService {

    @Override
    public Optional<Level> findById(BigInteger levelId) {
        return new LevelServiceDal().findById(levelId);
    }
}
