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
    private BigInteger studentProfileId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="guardians_first_name")
    private String guardiansFirstName;

    @Column(name="guardians_last_name")
    private String guardiansLastName;

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

    public BigInteger getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(BigInteger studentProfileId) {
        studentProfileId = studentProfileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGuardiansFirstName() {
        return guardiansFirstName;
    }

    public void setGuardiansFirstName(String guardiansFirstName) {
        this.guardiansFirstName = guardiansFirstName;
    }

    public String getGuardiansLastName() {
        return guardiansLastName;
    }

    public void setGuardiansLastName(String guardiansLastName) {
        this.guardiansLastName = guardiansLastName;
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