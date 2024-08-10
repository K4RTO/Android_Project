package com.example.ga_23s2.cloud.dataAccess;

import com.example.ga_23s2.model.UserId;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PublicReference {

  FirebaseFirestore store;

  public PublicReference() {
    this.store = FirebaseFirestore.getInstance();
  }

  public CollectionReference getCollection(Keys.CollectionName collectionName) {
    return store.collection(collectionName.KEY);
  }

  public CollectionReference getUserCollection() {
    return getCollection(Keys.CollectionName.PUBLIC_USER);
  }

  public CollectionReference getChatMessages() {
    return getCollection(Keys.CollectionName.MESSAGES);
  }

  public CollectionReference getPosts() {
    return getCollection(Keys.CollectionName.POSTS);
  }

  public DocumentReference getUserDocument(UserId userId) {
    return getUserCollection().document(userId.toString());
  }
}
