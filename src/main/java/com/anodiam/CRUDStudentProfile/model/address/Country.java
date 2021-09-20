package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

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

	@Transient
	private MessageResponse messageResponse;

	public Country(String countryName, String countryCode) {
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public Country() {
	}

	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
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
