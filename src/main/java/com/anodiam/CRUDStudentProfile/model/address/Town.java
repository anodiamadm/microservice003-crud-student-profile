package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mst_town",
		uniqueConstraints={@UniqueConstraint(name="uk_town_name", columnNames="town_name")},
		indexes={@Index(name="idx_town_name", columnList="town_name")})
public class Town {

	@Id
	@Column(name = "town_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger townId;

	@Column(name = "town_name", nullable = false, updatable = false, length = 255)
	private String townName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	@JsonBackReference
	@JsonIgnore
	private State state;

	@Transient
	private MessageResponse messageResponse;

	public Town(String townName) {
		this.townName = townName;
	}

	public Town() {
	}

	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}

	@JsonBackReference
	@JsonIgnore
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setTownId(BigInteger townId) {
		this.townId = townId;
	}

	public BigInteger getTownId() {
		return townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}
}
