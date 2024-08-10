package com.example.ga_23s2.cloud.authenticationStrategy;

import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.example.ga_23s2.viewmodel.authentication.ValidUserCredential;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Huanjie Zhang
 */
public class EmailStrategy implements AuthStrategy {
  FirebaseAuth auth;

  public EmailStrategy() {
    this.auth = FirebaseAuth.getInstance();
  }

  private boolean validCredential(UserCredential user) {
    if (!ValidUserCredential.validPwd(user.getPassword())) return false;
    return ValidUserCredential.validEmail(user.getEmail());
  }

  public Task<AuthResult> logIn(UserCredential user) {
    return auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
  }

  public Task<AuthResult> signUp(UserCredential user) {
    Task<AuthResult> task =
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
    // update account name
    return task;
  }
}
