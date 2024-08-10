package com.example.ga_23s2.model.post;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huanjie Zhang
 */
public class GameEntry implements Serializable {
  String name;
  String platforms;
  Double price;
  Date release_date;
  // ram and GPU as in gigabytes
  String RAM;
  int required_age;

  public GameEntry() {}

  public GameEntry(
      String name,
      String platforms,
      Double price,
      Date release_date,
      String RAM,
      int required_age) {
    this.name = name;
    this.platforms = platforms;
    this.price = price;
    this.release_date = release_date;
    this.RAM = RAM;
    this.required_age = required_age;
  }

  public GameEntry addName(String name) {
    this.name = name;
    return this;
  }

  public GameEntry addPlatforms(String platforms) {
    this.platforms = platforms;
    return this;
  }

  public GameEntry addReleaseDate(Date release_date) {
    this.release_date = release_date;
    return this;
  }

  public GameEntry addPrice(Double price) {
    this.price = price;
    return this;
  }

  public GameEntry addRAM(String RAM) {
    this.RAM = RAM;
    return this;
  }

  public GameEntry addRequireAge(int required_age) {
    this.required_age = required_age;
    return this;
  }

  public String getName() {
    if (name == null) return "";
    return name;
  }

  public String getPlatforms() {
    return platforms;
  }

  public Double getPrice() {
    return price;
  }

  public Date getRelease_date() {
    return release_date;
  }

  public String getRAM() {
    return RAM;
  }

  public int getRequired_age() {
    return required_age;
  }
}
