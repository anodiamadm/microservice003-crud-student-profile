package com.anodiam.CRUDStudentProfile.model.masterData;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_suburb",
		uniqueConstraints={@UniqueConstraint(name="uk_suburb_code", columnNames="suburb_code"),
				@UniqueConstraint(name="uk_suburb_name", columnNames="suburb_name")},
		indexes={@Index(name="idx_suburb_code", columnList="suburb_code"),
				@Index(name="idx_suburb_name", columnList="suburb_name")})
public class Suburb {

	@Id
	@Column(name = "suburb_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger suburbId;

	@Column(name = "suburb_name", nullable = false, updatable = false, length = 255)
	private String suburbName;

	@ManyToOne
	@JoinColumn(name = "town_id")
	private Town town;

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Suburb(String suburbName) {
		this.suburbName = suburbName;
	}

	public Suburb() {
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
}
