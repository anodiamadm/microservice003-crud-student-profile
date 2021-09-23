package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mst_suburb",
		uniqueConstraints={@UniqueConstraint(name="uk_suburb_name", columnNames="suburb_name"),
							@UniqueConstraint(name="uk_zip_code", columnNames="zip_code")},
		indexes={@Index(name="idx_suburb_name", columnList="suburb_name"),
					@Index(name="idx_zip_code", columnList="zip_code")})
public class Suburb {

	@Id
	@Column(name = "suburb_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger suburbId;

	@Column(name = "suburb_name", nullable = false, updatable = false, length = 255)
	private String suburbName;

	@Column(name = "zip_code", nullable = false, updatable = false, length = 15)
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "town_id")
	@JsonBackReference
	@JsonIgnore
	private Town town;

	@Transient
	private MessageResponse messageResponse;

	public Suburb(String suburbName) {
		this.suburbName = suburbName;
	}

	public Suburb() {
	}

	public MessageResponse getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}

	@JsonBackReference
	@JsonIgnore
	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public void setSuburbId(BigInteger suburbId) {
		this.suburbId = suburbId;
	}

	public BigInteger getSuburbId() {
		return suburbId;
	}

	public String getSuburbName() {
		return suburbName;
	}

	public void setSuburbName(String suburbName) {
		this.suburbName = suburbName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
