package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;

import java.util.List;

abstract class LevelServiceImpl implements LevelService {

    @Override
    public List<Level> findAll() {
        System.out.println("\n^^^^\n");
        System.out.println("\n^^LEVEL IMPL^^\n");
        System.out.println("\n^^^^^\n");
        return new LevelServiceDal().findAll();
    }
}
