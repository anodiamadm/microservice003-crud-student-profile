package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

abstract class BoardServiceImpl implements BoardService {

    @Override
    public Optional<Board> findById(BigInteger boardId) {
        return new BoardServiceDal().findById(boardId);
    }
}
