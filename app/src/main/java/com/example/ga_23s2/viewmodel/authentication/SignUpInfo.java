package com.example.ga_23s2.viewmodel.authentication;

/**
 * @author Huanjie Zhang
 */
public class SignUpInfo extends UserCredential {
  private final String accountName; // or displayed name
  private final Integer age;

  public SignUpInfo(String accountName, Integer age) {
    this.accountName = accountName;
    this.age = age;
  }

  public SignUpInfo(UserCredential userCredential) {
    this.accountName = "Anonymous";
    this.age = 0;
  }

  public String getAccountName() {
    return accountName;
  }

  public Integer getAge() {
    return age;
  }
}
