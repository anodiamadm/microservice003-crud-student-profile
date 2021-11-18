package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_suburb")
public class Suburb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="suburb_id")
	private BigInteger suburbId;

	@Column(name="suburb_name")
	private String suburbName;

	@Column(name="zip_code")
	private String zipCode;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "townId", referencedColumnName = "town_id")
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
