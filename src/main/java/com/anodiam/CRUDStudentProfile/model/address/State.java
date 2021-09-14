package com.anodiam.CRUDStudentProfile.model.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mst_state",
		uniqueConstraints={@UniqueConstraint(name="uk_state_name", columnNames="state_name"),
							@UniqueConstraint(name="uk_state_code", columnNames="state_code")},
		indexes={@Index(name="idx_state_name", columnList="state_name"),
					@Index(name="idx_state_code", columnList="state_code")})
public class State {

	@Id
	@Column(name = "state_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger stateId;

	@Column(name = "state_name", nullable = false, updatable = false, length = 255)
	private String stateName;

	@Column(name = "state_code", nullable = false, updatable = false, length = 15)
	private String stateCode;

	@ManyToOne
	@JoinColumn(name = "country_id")
	@JsonBackReference
	@JsonIgnore
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Town> townList = new ArrayList<>();

	public State(String stateName, String stateCode) {
		this.stateName = stateName;
		this.stateCode = stateCode;
	}

	public State() {
	}

	public List<Town> getTownList() {
		return townList;
	}

	public void setTownList(List<Town> townList) {
		this.townList = townList;
	}

	@JsonBackReference
	@JsonIgnore
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
