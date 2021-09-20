package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BoardServiceDal extends BoardServiceImpl {

    @Autowired
    private BoardRepository boardRepository;

    public BoardServiceDal(){}

    @Override
    public List<Board> findAll() {
        return (List<Board>) boardRepository.findAll();
    }
}
