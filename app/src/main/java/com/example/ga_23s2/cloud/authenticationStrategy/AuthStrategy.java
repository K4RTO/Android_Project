package com.example.ga_23s2.cloud.authenticationStrategy;

import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * @author Huanjie Zhang
 */
public interface AuthStrategy {
  Task<AuthResult> logIn(UserCredential user);

  Task<AuthResult> signUp(UserCredential user);
}
