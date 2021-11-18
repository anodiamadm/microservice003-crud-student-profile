package com.anodiam.CRUDStudentProfile.model.masterData;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="board_id")
	private BigInteger boardId;

	@Column(name="board_name")
	private String boardName;

	@Column(name="board_short_name")
	private String boardShortName;

	@Transient
	private MessageResponse messageResponse;

	public Board(String boardName, String boardShortName) {
		this.boardName = boardName;
		this.boardShortName = boardShortName;
	}

	public Board() {
	}

	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}

	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}

	public BigInteger getBoardId() {
		return boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardShortName() {
		return boardShortName;
	}

	public void setBoardShortName(String boardShortName) {
		this.boardShortName = boardShortName;
	}
}
