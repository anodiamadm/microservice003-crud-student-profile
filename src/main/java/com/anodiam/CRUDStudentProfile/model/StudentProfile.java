package com.anodiam.CRUDStudentProfile.model;

import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import com.anodiam.CRUDStudentProfile.model.masterData.Language;
import com.anodiam.CRUDStudentProfile.model.masterData.Level;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_profile_id")
    private BigInteger student_profile_id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="phone_number")
    private String phoneNumber;

    private String address;

    private Double latitude;

    private Double longitude;

    @Column(name="guardians_name")
    private String guardiansName;

    @Column(name="guardians_email")
    private String guardiansEmail;

    @Column(name="guardians_phone_number")
    private String guardiansPhoneNumber;

    @Column(name="profile_image_link")
    private String profileImageLink;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "boardId", referencedColumnName = "board_id")
    private Board board;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "levelId", referencedColumnName = "level_id")
    private Level level;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "suburbId", referencedColumnName = "suburb_id")
    private Suburb suburb;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;

    @Transient
    private MessageResponse messageResponse;

    public StudentProfile(){}

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public BigInteger getStudent_profile_id() {
        return student_profile_id;
    }

    public void setStudent_profile_id(BigInteger student_profile_id) {
        this.student_profile_id = student_profile_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getGuardiansName() {
        return guardiansName;
    }

    public void setGuardiansName(String guardiansName) {
        this.guardiansName = guardiansName;
    }

    public String getGuardiansEmail() {
        return guardiansEmail;
    }

    public void setGuardiansEmail(String guardiansEmail) {
        this.guardiansEmail = guardiansEmail;
    }

    public String getGuardiansPhoneNumber() {
        return guardiansPhoneNumber;
    }

    public void setGuardiansPhoneNumber(String guardiansPhoneNumber) {
        this.guardiansPhoneNumber = guardiansPhoneNumber;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Suburb getSuburb() {
        return suburb;
    }

    public void setSuburb(Suburb suburb) {
        this.suburb = suburb;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}