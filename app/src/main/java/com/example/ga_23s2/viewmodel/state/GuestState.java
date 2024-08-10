package com.example.ga_23s2.viewmodel.state;

import com.example.ga_23s2.cloud.CloudResult;
import com.example.ga_23s2.cloud.authenticationStrategy.AuthStrategy;
import com.example.ga_23s2.cloud.authenticationStrategy.EmailStrategy;
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
public class GuestState implements State {
  AuthStrategy authStrategy = new EmailStrategy();

  /** Current default logIn is email log in */
  @Override
  public CloudResult<AuthResult> logIn(UserCredential userCredential) {
    return new CloudResult<>(authStrategy.logIn(userCredential), "login");
  }

  @Override
  public String logOut() {
    FirebaseAuth.getInstance().signOut();
    return "Cannot log out at guest state.";
  }

  @Override
  public CloudResult<AuthResult> signUp(UserCredential userCredential) {
    return new CloudResult<>(authStrategy.signUp(userCredential), "sign-up");
  }

  @Override
  public void changeLoginMethod(LoginMethod loginMethod) {
    authStrategy = new EmailStrategy();
    switch (loginMethod) {
      case EMAIL:
        authStrategy = new EmailStrategy();
        //      case FACEBOOK: authStrategy = new FacebookStrategy();
      default:
        return;
    }
  }

  @Override
  public void createPost() {}

  @Override
  public void forbiddenUser() {}

  @Override
  public UserId getCurrentUserId() {
    return null;
  }

  @Override
  public DocumentReference getUserDocument() {
    return null;
  }

  @Override
  public CollectionReference getChatCollection() {
    return null;
  }
}
