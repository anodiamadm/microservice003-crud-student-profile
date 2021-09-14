package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
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
@Table(name = "mst_country",
		uniqueConstraints={@UniqueConstraint(name="uk_country_name", columnNames="country_name"),
							@UniqueConstraint(name="uk_country_code", columnNames="country_code")},
		indexes={@Index(name="idx_country_name", columnList="country_name"),
					@Index(name="idx_country_code", columnList="country_code")})
public class Country {

	@Id
	@Column(name = "country_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger countryId;

	@Column(name = "country_name", nullable = false, updatable = false, length = 255)
	private String countryName;

	@Column(name = "country_code", nullable = false, updatable = false, length = 15)
	private String countryCode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<State> stateList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<StudentProfile> studentProfileList = new ArrayList<>();


	public Country(String countryName, String countryCode) {
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public Country() {
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public List<StudentProfile> getStudentProfileList() {
		return studentProfileList;
	}

	public void setStudentProfileList(List<StudentProfile> studentProfileList) {
		this.studentProfileList = studentProfileList;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
