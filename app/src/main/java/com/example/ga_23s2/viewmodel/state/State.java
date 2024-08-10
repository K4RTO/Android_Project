package com.example.ga_23s2.viewmodel.state;

import com.example.ga_23s2.cloud.CloudResult;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.viewmodel.authentication.LoginMethod;
import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

/**
 * Manages the accessibility to activities from different users. It works as default of logged in
 * common user.
 *
 * @author Huanjie Zhang
 */
public interface State {
  CloudResult<AuthResult> logIn(UserCredential userCredential);

  String logOut();

  CloudResult<AuthResult> signUp(UserCredential userCredential);

  void changeLoginMethod(LoginMethod loginMethod);

  void createPost();

  void forbiddenUser();

  UserId getCurrentUserId();

  DocumentReference getUserDocument();

  CollectionReference getChatCollection();
}
