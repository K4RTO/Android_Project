package com.example.ga_23s2.model.p2p;

import java.util.Date;

/**
 * @author Yifan Xiao
 */
public class ChatMessage {
  public String senderId, receiverId, message, dateTime;
  public Date dateObject;

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(String receiverId) {
    this.receiverId = receiverId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public Date getDateObject() {
    return dateObject;
  }

  public void setDateObject(Date dateObject) {
    this.dateObject = dateObject;
  }
}
