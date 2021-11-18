package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="state_id")
	private BigInteger stateId;

	@Column(name="state_name")
	private String stateName;

	@Column(name="state_code")
	private String stateCode;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
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
