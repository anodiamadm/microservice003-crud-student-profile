package com.anodiam.CRUDStudentProfile.model.masterData;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mst_level",
		uniqueConstraints={@UniqueConstraint(name="uk_level_name", columnNames="level_name"),
							@UniqueConstraint(name="uk_level_code", columnNames="level_code")},
		indexes={@Index(name="idx_level_name", columnList="level_name"),
					@Index(name="idx_level_code", columnList="level_code")})
public class Level {

	@Id
	@Column(name = "level_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger levelId;

	@Column(name = "level_name", nullable = false, updatable = false, length = 63)
	private String levelName;

	@Column(name = "level_code", nullable = false, updatable = false, length = 15)
	private String levelCode;

	@Transient
	private MessageResponse returnMessage;

	public Level(String levelName, String levelCode) {
		this.levelName = levelName;
		this.levelCode = levelCode;
	}

	public Level() {
	}

	public MessageResponse getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(MessageResponse returnMessage) {
		this.returnMessage = returnMessage;
	}

	public void setLevelId(BigInteger levelId) {
		this.levelId = levelId;
	}

	public BigInteger getLevelId() {
		return levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
}
