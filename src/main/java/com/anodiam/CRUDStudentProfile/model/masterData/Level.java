package com.anodiam.CRUDStudentProfile.model.masterData;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_level")
public class Level {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="level_id")
	private BigInteger levelId;

	@Column(name="level_name")
	@JsonBackReference
	@JsonIgnore
	private String levelName;

	@Column(name="level_code")
	@JsonBackReference
	@JsonIgnore
	private String levelCode;

	@Transient
	@JsonBackReference
	@JsonIgnore
	private MessageResponse messageResponse;

	public Level() {}

	@JsonBackReference
	@JsonIgnore
	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}

	public void setLevelId(BigInteger levelId) {
		this.levelId = levelId;
	}

	public BigInteger getLevelId() {
		return levelId;
	}

	@JsonBackReference
	@JsonIgnore
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@JsonBackReference
	@JsonIgnore
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
}
