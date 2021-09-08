package com.anodiam.CRUDStudentProfile.model.masterData;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_state",
		uniqueConstraints={@UniqueConstraint(name="uk_state_code", columnNames="state_code"),
				@UniqueConstraint(name="uk_state_name", columnNames="state_name")},
		indexes={@Index(name="idx_state_code", columnList="state_code"),
				@Index(name="idx_state_name", columnList="state_name")})
public class State {

	@Id
	@Column(name = "state_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger stateId;

	@Column(name = "state_code", nullable = false, updatable = false, length = 15)
	private String stateCode;

	@Column(name = "state_name", nullable = false, updatable = false, length = 255)
	private String stateName;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private List<Town> townList = new ArrayList<>();

	public List<Town> getTownList() {
		return townList;
	}

	public void setTownList(List<Town> townList) {
		this.townList = townList;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public State(String stateName) {
		this.stateName = stateName;
	}

	public State() {
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
}
