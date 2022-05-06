package com.anodiam.CRUDStudentProfile.model.masterData;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonBackReference
	@JsonIgnore
	private String boardName;

	@Column(name="board_code")
	@JsonBackReference
	@JsonIgnore
	private String boardCode;

	@Transient
	@JsonBackReference
	@JsonIgnore
	private MessageResponse messageResponse;

	public Board() {
	}

	@JsonBackReference
	@JsonIgnore
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

	@JsonBackReference
	@JsonIgnore
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	@JsonBackReference
	@JsonIgnore
	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
}
