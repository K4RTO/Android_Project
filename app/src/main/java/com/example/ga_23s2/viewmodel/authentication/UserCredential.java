package com.example.ga_23s2.viewmodel.authentication;

/**
 * @author Zhaoyan Cong
 */
public class UserCredential {
  private String email;
  private String password;
  private String accountName;

  public UserCredential(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public UserCredential() {}

  public UserCredential addEmail(String email) {
    this.email = email;
    return this;
  }

  public UserCredential addPassword(String password) {
    this.password = password;
    return this;
  }

  public UserCredential addAccountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getAccountName() {
    return accountName;
  }

  public boolean hasAccountName() {
    return accountName != null && !accountName.equals("");
  }
}
