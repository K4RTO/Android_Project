package com.example.ga_23s2.model;

import java.io.Serializable;

/**
 * @author Huanjie Zhang
 */
public class User implements Serializable {

  String userId;
  String email;
  String profileImg;
  String name;
  String token;
  String location;
  Integer age;

  public User(String email, String profileImg, String name, Integer age) {
    this.email = email;
    this.profileImg = profileImg;
    this.name = name;
    this.age = age;
  }

  public User() {}

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public String getProfileImg() {
    return profileImg;
  }

  public User addUserId(String id) {
    this.userId = id;
    return this;
  }

  public User addLocation(String location) {
    this.location = location;
    return this;
  }

  public User addEmail(String email) {
    this.email = email;
    return this;
  }

  public User addProfileImg(String profileImg) {
    this.profileImg = profileImg;
    return this;
  }

  public User addName(String name) {
    this.name = name;
    return this;
  }

  public User addAge(Integer age) {
    this.age = age;
    return this;
  }

  public User addToken(String token) {
    this.token = token;
    return this;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setProfileImg(String profileImg) {
    this.profileImg = profileImg;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
