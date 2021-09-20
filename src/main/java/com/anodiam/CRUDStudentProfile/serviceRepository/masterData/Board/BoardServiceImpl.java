package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;

import java.util.List;

abstract class BoardServiceImpl implements BoardService {

    @Override
    public List<Board> findAll() { return new BoardServiceDal().findAll(); }
}
