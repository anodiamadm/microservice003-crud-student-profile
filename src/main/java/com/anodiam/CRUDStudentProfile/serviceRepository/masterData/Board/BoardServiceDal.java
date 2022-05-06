package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
class BoardServiceDal extends BoardServiceImpl {

    @Autowired
    private BoardRepository boardRepository;

    public BoardServiceDal(){}

    @Override
    public Optional<Board> findById(BigInteger boardId) {
        Board boardReturned = new Board();
        try {
            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            if(optionalBoard.isPresent()){
                boardReturned = optionalBoard.get();
                boardReturned.setMessageResponse(new
                        MessageResponse(ResponseCode.SUCCESS.getID(),
                        ResponseCode.SUCCESS.getMessage() + boardReturned.getBoardName()));
            } else {
                boardReturned.setMessageResponse(new MessageResponse(ResponseCode.BOARD_ID_NOT_FOUND.getID(),
                        ResponseCode.BOARD_ID_NOT_FOUND.getMessage() + boardId));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            boardReturned.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage() + exception.getMessage()));
        }
        return Optional.of(boardReturned);
    }
}
