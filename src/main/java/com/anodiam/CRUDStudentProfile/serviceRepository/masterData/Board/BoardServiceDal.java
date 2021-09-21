package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BoardServiceDal extends BoardServiceImpl {

    @Autowired
    private BoardService boardService;

    public BoardServiceDal(){}

    @Override
    public List<Board> findAll() {
        try {
            List<Board> boards = boardService.findAll();
            if(!boards.isEmpty()) {
                return boards;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
