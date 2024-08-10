package com.example.ga_23s2.viewmodel;

import android.content.res.Resources;
import com.example.ga_23s2.cloud.CloudResult;
import com.example.ga_23s2.cloud.dataAccess.PublicReference;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.view.fragmentHandler.FragmentHandler;
import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.example.ga_23s2.viewmodel.state.AdminState;
import com.example.ga_23s2.viewmodel.state.GuestState;
import com.example.ga_23s2.viewmodel.state.State;
import com.example.ga_23s2.viewmodel.state.UserState;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

/**
 * @author Huanjie Zhang Communicates with back end and keeps/logs saved information.
 */
public class Session {
  private State state;
  private static Session session;
  private FragmentHandler fragmentHandler;
  private Resources rss;

  private Session() {
    this.state = new GuestState();
  }

  // singleton
  public static Session getInstance() {
    if (session == null) {
      session = new Session();
    }
    return session;
  }

  public void changeState(State newState) {
    if (newState instanceof UserState) notifyLoggedIn();
    else if (newState instanceof GuestState) notifyLoggedOut();
    state = newState;
  }

  public boolean isAdminState() {
    return state instanceof AdminState;
  }

  public CloudResult<AuthResult> logIn(UserCredential userCredential) {
    return state.logIn(userCredential);
  }

  // to be implemented when necessary
  public void changeLoginMethod() {}

  public String logOut() {
    changeState(new GuestState());
    return state.logOut();
  }

  public CloudResult<AuthResult> signUp(UserCredential userCredential) {
    return state.signUp(userCredential);
  }

  public boolean isGuestState() {
    return state instanceof GuestState;
  }

  public void setFragmentHandler(FragmentHandler fragmentHandler) {
    session.fragmentHandler = fragmentHandler;
  }

  public void notifyLoggedIn() {
    fragmentHandler.switchToLoggedIn();
  }

  public void notifyLoggedOut() {
    fragmentHandler.switchToLoggedOut();
  }

  public UserId getCurrentUserId() {
    return state.getCurrentUserId();
  }

  public DocumentReference getUserDocument() {
    return state.getUserDocument();
  }

  public DocumentReference getUserDocument(UserId userId) {
    PublicReference publicReference = new PublicReference();
    return publicReference.getUserDocument(userId);
  }

  public CollectionReference getPostsCollection() {
    return new PublicReference().getPosts();
  }

  public CollectionReference getChatCollection() {
    return state.getChatCollection();
  }

  public CollectionReference getUserCollection() {
    return new PublicReference().getUserCollection();
  }

  public void setResources(Resources rss) {
    session.rss = rss;
  }

  public String getStringResource(int id) {
    return rss.getString(id);
  }
}
