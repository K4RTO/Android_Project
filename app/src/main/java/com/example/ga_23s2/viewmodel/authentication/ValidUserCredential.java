package com.example.ga_23s2.viewmodel.authentication;

import java.util.HashSet;
import java.util.Set;

/**
 * Verify input from gui. Motivation: prevent SQL injection. i.e. All inputs should not contain word
 * "SELECT" in upper/lower case.
 *
 * <p>- Should limit the use of special character. - Should limit the length of characters. - Should
 * meet specific forms.
 */
/**
 * @author Zhaoyan Cong
 */
public class ValidUserCredential {
  private static final int MAX_CHAR = 50; // maybe
  private static final Set<Character> excludeChars =
      new HashSet<>(); // Characters that should not show up in usernames or passwords

  static {
    excludeChars.add(';');
    excludeChars.add(':');
    excludeChars.add('\'');
    excludeChars.add('"');
    excludeChars.add('`');
    // Add more special characters if needed
  }

  /**
   * Checking if the length of username/password is valid
   *
   * @param input A string of username/password
   * @return True if the length of the input is valid
   */
  public static boolean goodLength(String input) {
    return input != null && input.length() > 0 && input.length() <= MAX_CHAR;
  }

  /**
   * Checking if the string input only contains valid characters
   *
   * @param input A string of username/password
   * @return True if the input only contains valid characters
   */
  public static boolean goodChars(String input) {
    if (input == null) return false;
    for (char c : input.toCharArray()) {
      if (excludeChars.contains(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checking if user's password is valid
   *
   * @param pwd A string representing user's password
   * @return True if user's password is valid
   */
  public static boolean validPwd(String pwd) {
    return goodLength(pwd) && goodChars(pwd) && !pwd.toLowerCase().contains("select");
  }

  /**
   * Checking if user's account name is valid
   *
   * @param name A string representing user's account name
   * @return True if user's name is valid
   */
  public static boolean validAccount(String name) {
    return goodLength(name) && goodChars(name) && !name.toLowerCase().contains("select");
  }

  /**
   * Checking if user's email address is valid, format of an email address should be like "**@**.**"
   *
   * @param email A string representing user's email address
   * @return True if user's email address is valid
   */
  public static boolean validEmail(String email) {
    String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    return email != null && email.matches(regex) && validAccount(email);
  }

  /**
   * Checking if user's user credential is valid
   *
   * @param userCredential User's user credential
   * @return True if user's user credential is valid
   */
  public static boolean validUserCredential(UserCredential userCredential) {
    // handles other accountName or password format.
    return validAccount(userCredential.getEmail()) && validPwd(userCredential.getPassword());
  }
}
