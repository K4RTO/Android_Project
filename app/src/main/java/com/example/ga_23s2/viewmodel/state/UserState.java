package com.example.ga_23s2.viewmodel.state;

import com.example.ga_23s2.cloud.CloudResult;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.PublicReference;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.viewmodel.authentication.LoginMethod;
import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

/**
 * @author Huanjie Zhang
 */
public class UserState implements State {
  UserId currentUserId;

  public UserState(UserId currentUserId) {
    this.currentUserId = currentUserId;
  }

  @Override
  public CloudResult<AuthResult> logIn(UserCredential userCredential) {
    return null;
  }

  @Override
  public String logOut() {
    FirebaseAuth.getInstance().signOut();
    return "Successfully log out";
  }

  @Override
  public CloudResult<AuthResult> signUp(UserCredential userCredential) {
    return null;
  }

  @Override
  public void changeLoginMethod(LoginMethod loginMethod) {}

  @Override
  public void createPost() {}

  @Override
  public void forbiddenUser() {}

  @Override
  public UserId getCurrentUserId() {
    return currentUserId;
  }

  @Override
  public DocumentReference getUserDocument() {
    DataAccess da = new DataAccess();
    return da.getUser(currentUserId.toString());
  }

  @Override
  public CollectionReference getChatCollection() {
    return new PublicReference().getChatMessages();
  }
}
