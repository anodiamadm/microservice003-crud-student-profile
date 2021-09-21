package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LevelServiceDal extends LevelServiceImpl {

    @Autowired
    private LevelRepository levelRepository;

    public LevelServiceDal(){}

    @Override
    public List<Level> findAll() {
        try {
            List<Level> levels = levelRepository.findAll();
            if(!levels.isEmpty()) {
                return levels;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
