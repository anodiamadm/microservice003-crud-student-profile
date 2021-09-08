package com.anodiam.CRUDStudentProfile.model.masterData;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_country",
		uniqueConstraints={@UniqueConstraint(name="uk_country_code", columnNames="country_code"),
				@UniqueConstraint(name="uk_country_name", columnNames="country_name")},
		indexes={@Index(name="idx_country_code", columnList="country_code"),
				@Index(name="idx_country_name", columnList="country_name")})
public class Country {

	@Id
	@Column(name = "country_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger countryId;

	@Column(name = "country_code", nullable = false, updatable = false, length = 15)
	private String countryCode;

	@Column(name = "country_name", nullable = false, updatable = false, length = 255)
	private String countryName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	private List<State> stateList = new ArrayList<>();

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Country(String countryName) {
		this.countryName = countryName;
	}

	public Country() {
	}

	public void setCountryId(BigInteger countryId) {
		this.countryId = countryId;
	}

	public BigInteger getCountryId() {
		return countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
