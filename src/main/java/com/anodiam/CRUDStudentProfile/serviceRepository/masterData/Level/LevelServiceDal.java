package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceDal extends LevelServiceImpl {

    @Autowired
    private LevelRepository levelRepository;

    public LevelServiceDal(){}

    @Override
    public List<Level> findAll() {
        return (List<Level>) levelRepository.findAll();
    }
}
