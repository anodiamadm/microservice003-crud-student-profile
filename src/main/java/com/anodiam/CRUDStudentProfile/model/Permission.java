package com.anodiam.CRUDStudentProfile.model;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "mst_permission",
		uniqueConstraints={@UniqueConstraint(name="uk_permission_name", columnNames="permission_name")},
		indexes={@Index(name="idx_permission_name", columnList="permission_name")})
public class Permission {

	@Id
	@Column(name = "permission_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger permissionId;

	@Column(name = "permission_name", nullable = false, updatable = false, length = 255)
	private String permissionName;

	@ManyToMany(mappedBy = "permissionList")
//	@JsonBackReference
//	@JsonIgnore
	private Collection<User> userList = new ArrayList<>();

	public Permission(String permissionName) {
		this.permissionName = permissionName;
	}

	public Permission() {
	}

	public void setPermissionId(BigInteger permissionId) {
		this.permissionId = permissionId;
	}

	public BigInteger getPermissionId() {
		return permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	//	@JsonBackReference
//	@JsonIgnore
	public Collection<User> getUserList() {
		return userList;
	}

	public void setUserList(Collection<User> userList) {
		this.userList = userList;
	}
}
