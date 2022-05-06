package com.anodiam.CRUDStudentProfile.model;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger userId;

    private int active;

    @JsonBackReference
    @JsonIgnore
    private Date dateCreated;

    @JsonBackReference
    @JsonIgnore
    private String password;

    @JsonBackReference
    @JsonIgnore
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonBackReference
    @JsonIgnore
    private List<Role> roleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonBackReference
    @JsonIgnore
    private List<Permission> permissionList = new ArrayList<>();

    @Transient
    @JsonBackReference
    @JsonIgnore
    private MessageResponse messageResponse;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.active=1;
        this.dateCreated = new Date();
    }

    @JsonBackReference
    @JsonIgnore
    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @JsonBackReference
    @JsonIgnore
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigInteger getUserId() {
        return userId;
    }

    @JsonBackReference
    @JsonIgnore
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonBackReference
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonBackReference
    @JsonIgnore
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @JsonBackReference
    @JsonIgnore
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonBackReference
    @JsonIgnore
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

}