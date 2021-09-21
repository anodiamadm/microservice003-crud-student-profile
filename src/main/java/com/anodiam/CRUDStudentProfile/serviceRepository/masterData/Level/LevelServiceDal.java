package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level;

import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LevelServiceDal extends LevelServiceImpl {

    @Autowired
    private LevelService levelService;

    public LevelServiceDal(){}

    @Override
    public List<Level> findAll() {
        System.out.println("\n$$$$\n");
        System.out.println("\n$$LEVEL DAL$$\n");
        System.out.println("\n$$$$\n");
        try {
            List<Level> levels = levelService.findAll();
            if(!levels.isEmpty()) {
                return levels;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
