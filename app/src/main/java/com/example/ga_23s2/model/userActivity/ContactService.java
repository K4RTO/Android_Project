package com.example.ga_23s2.model.userActivity;

import com.example.ga_23s2.model.UserId;
import java.util.Set;

/**
 * @author Yifan Xiao
 */
public class ContactService {
  Set<UserId> contacts;

  public boolean searchByEmail(String email) {
    // call method in cloud, check on firebase
    return false;
  }

  private UserId getKey(UserId a, UserId b) {
    return null;
  }

  public boolean addToContact() {
    // should check blocked or not.
    return false;
  }

  public void removeContact() {
    // not necessarily block
    // remove from list and chat history.
  }

  public boolean canChatWith() {
    return false;
  }
}
