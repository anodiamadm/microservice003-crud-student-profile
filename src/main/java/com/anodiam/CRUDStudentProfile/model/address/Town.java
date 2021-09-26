package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_town")
public class Town {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger townId;

	private String townName;

	@ManyToOne
	@JoinColumn(name = "state_id")
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
