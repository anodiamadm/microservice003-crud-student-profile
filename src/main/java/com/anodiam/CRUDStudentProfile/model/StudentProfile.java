package com.anodiam.CRUDStudentProfile.model;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "student_profile",
        uniqueConstraints={@UniqueConstraint(name="uk_email", columnNames="email")},
        indexes={@Index(name="idx_first_name", columnList="first_name"),
                @Index(name="idx_middle_name", columnList="middle_name"),
                @Index(name="idx_last_name", columnList="last_name"),
                @Index(name="idx_email", columnList="email"),
                @Index(name="idx_phone_number", columnList="phone_number"),
                @Index(name="idx_guardians_email", columnList="guardians_email"),
                @Index(name="idx_guardians_phone_number", columnList="guardians_phone_number")})
//                @Index(name="idx_board_id", columnList="board_id"),
//                @Index(name="idx_level_id", columnList="level_id"),
//                @Index(name="idx_address", columnList="country_id,state_id")
//                        "town_id,suburb_id")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_profile_id", nullable = false)
    private BigInteger studentProfileId;

    @Column(name = "first_name", nullable = true, length = 255)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 255)
    private String middleName;

    @Column(name = "last_name", nullable = true, length = 255)
    private String lastName;

    @Column(name = "email", nullable = true, length = 255)
    private String email;

    @Column(name = "phone_number", nullable = true, length = 255)
    private String phoneNumber;

    @Column(name = "guardians_first_name", nullable = true, length = 255)
    private String guardiansFirstName;

    @Column(name = "guardians_last_name", nullable = true, length = 255)
    private String guardiansLastName;

    @Column(name = "guardians_email", nullable = true, length = 255)
    private String guardiansEmail;

    @Column(name = "guardians_phone_number", nullable = true, length = 255)
    private String guardiansPhoneNumber;

    @Column(name = "profile_image_link", nullable = true, length = 1023)
    private String profileImageLink;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    @JsonIgnore
    private Board board;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonBackReference
    @JsonIgnore
    private Level level;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    @JsonIgnore
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonBackReference
    @JsonIgnore
    private State state;

    @ManyToOne
    @JoinColumn(name = "town_id")
    @JsonBackReference
    @JsonIgnore
    private Town town;

    @ManyToOne
    @JoinColumn(name = "suburb_id")
    @JsonBackReference
    @JsonIgnore
    private Suburb suburb;

    protected StudentProfile(){}

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

    @JsonBackReference
    @JsonIgnore
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @JsonBackReference
    @JsonIgnore
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @JsonBackReference
    @JsonIgnore
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonBackReference
    @JsonIgnore
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @JsonBackReference
    @JsonIgnore
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @JsonBackReference
    @JsonIgnore
    public Suburb getSuburb() {
        return suburb;
    }

    public void setSuburb(Suburb suburb) {
        this.suburb = suburb;
    }
}