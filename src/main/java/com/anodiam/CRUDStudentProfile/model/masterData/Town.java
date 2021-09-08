package com.anodiam.CRUDStudentProfile.model.masterData;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_town",
		uniqueConstraints={@UniqueConstraint(name="uk_town_code", columnNames="town_code"),
				@UniqueConstraint(name="uk_town_name", columnNames="town_name")},
		indexes={@Index(name="idx_town_code", columnList="town_code"),
				@Index(name="idx_town_name", columnList="town_name")})
public class Town {

	@Id
	@Column(name = "town_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger townId;

	@Column(name = "town_name", nullable = false, updatable = false, length = 255)
	private String townName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "town")
	private List<Suburb> suburbList = new ArrayList<>();

	public List<Suburb> getSuburbList() {
		return suburbList;
	}

	public void setSuburbList(List<Suburb> suburbList) {
		this.suburbList = suburbList;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Town(String townName) {
		this.townName = townName;
	}

	public Town() {
	}

	public void setTownId(BigInteger townId) {
		this.townId = townId;
	}

	public BigInteger getTownId() {
		return townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}
}
