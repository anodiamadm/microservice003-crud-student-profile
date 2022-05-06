package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BoardService {
    Optional<Board> findById(BigInteger boardId);
}
