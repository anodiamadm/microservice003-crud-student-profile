package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger stateId;

	private String stateName;

	private String stateCode;

	@ManyToOne
	@JoinColumn(name = "country_id")
	@JsonBackReference
	private Country country;

	@Transient
	private MessageResponse messageResponse;

	public State(String stateName, String stateCode) {
		this.stateName = stateName;
		this.stateCode = stateCode;
	}

	public State() {
	}

	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}

	@JsonBackReference
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setStateId(BigInteger stateId) {
		this.stateId = stateId;
	}

	public BigInteger getStateId() {
		return stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
