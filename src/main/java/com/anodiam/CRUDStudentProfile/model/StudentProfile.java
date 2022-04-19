package com.anodiam.CRUDStudentProfile.model;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
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

    @Column(name="full_name")
    private String fullName;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "boardId", referencedColumnName = "board_id")
    private Board board;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "levelId", referencedColumnName = "level_id")
    private Level level;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Transient
    private MessageResponse messageResponse;

    @Transient
    private BigInteger boardId;

    @Transient
    private BigInteger levelId;

    public StudentProfile(){}

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public BigInteger getBoardId() {
        return boardId;
    }

    public void setBoardId(BigInteger boardId) {
        this.boardId = boardId;
    }

    public BigInteger getLevelId() {
        return levelId;
    }

    public void setLevelId(BigInteger levelId) {
        this.levelId = levelId;
    }

    public BigInteger getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(BigInteger studentProfileId) {
        this.studentProfileId = studentProfileId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}