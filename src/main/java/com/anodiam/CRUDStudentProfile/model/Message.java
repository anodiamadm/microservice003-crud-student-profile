package com.anodiam.CRUDStudentProfile.model;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

import javax.persistence.*;

@Entity
@Table(name = "mst_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private Integer messageId;

    @Column(name="message_code")
    private String messageCode;

    @Column(name="message_desc")
    private String messageDesc;

    @Transient
    private MessageResponse messageResponse;

    public Message(Integer messageId,String messageCode,String messageDesc) {
        this.messageId = messageId;
        this.messageCode = messageCode;
        this.messageDesc=messageDesc;
    }

    protected Message(){}

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }
}