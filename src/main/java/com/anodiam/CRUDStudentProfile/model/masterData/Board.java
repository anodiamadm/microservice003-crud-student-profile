package com.anodiam.CRUDStudentProfile.model.masterData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mst_board",
		uniqueConstraints={@UniqueConstraint(name="uk_board_name", columnNames="board_name"),
							@UniqueConstraint(name="uk_board_short_name", columnNames="board_short_name")},
		indexes={@Index(name="idx_board_name", columnList="board_name"),
					@Index(name="idx_board_short_name", columnList="board_short_name")})
public class Board {

	@Id
	@Column(name = "board_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger boardId;

	@Column(name = "board_name", nullable = false, updatable = false, length = 255)
	private String boardName;

	@Column(name = "board_short_name", nullable = false, updatable = false, length = 15)
	private String boardCode;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "board")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JsonManagedReference
//	private List<StudentProfile> studentProfileList = new ArrayList<>();

	public Board(String boardName, String boardCode) {
		this.boardName = boardName;
		this.boardCode = boardCode;
	}

	public Board() {
	}

//	public List<State> getStateList() {
//		return stateList;
//	}
//
//	public void setStateList(List<State> stateList) {
//		this.stateList = stateList;
//	}

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

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

//	public List<StudentProfile> getStudentProfileList() {
//		return studentProfileList;
//	}
//
//	public void setStudentProfileList(List<StudentProfile> studentProfileList) {
//		this.studentProfileList = studentProfileList;
//	}
}
