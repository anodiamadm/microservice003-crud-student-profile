package com.anodiam.CRUDStudentProfile.model.address;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "mst_country")
public class Country
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger country_id;

	private String country_code;

	private String country_name;

	public Country() {}

	@Transient
	private MessageResponse messageResponse;

	public Country(BigInteger country_id,String country_code, String country_name) {
		this.country_id=country_id;
		this.country_code = country_code;
		this.country_name = country_name;
	}

	public BigInteger getCountry_id() {
		return country_id;
	}

	public void setCountry_id (BigInteger country_id){
		this.country_id = country_id;
	}

	public String getCountry_code () {
		return country_code;
	}

	public void setCountry_code (String country_code){
		this.country_code = country_code;
	}

	public String getCountry_name () {
		return country_name;
	}

	public void setCountry_name (String country_name){
		this.country_name = country_name;
	}

	public MessageResponse getMessageResponse () {
		return messageResponse;
	}

	public void setMessageResponse (MessageResponse messageResponse){
		this.messageResponse = messageResponse;
	}

}

