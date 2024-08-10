package com.example.ga_23s2.cloud.dataAccess;

import com.example.ga_23s2.cloud.SnapshotParser;
import com.example.ga_23s2.model.User;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.post.PostId;
import com.example.ga_23s2.view.accountFragment.BookmarkCallback;
import com.example.ga_23s2.view.p2pFragment.UserAdapterCallback;
import com.example.ga_23s2.view.userProfile.UserCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Yifan Xiao
 */
public class DataAccess {
  FirebaseFirestore database;

  public DataAccess dataAccess;

  public DataAccess() {
    this.database = FirebaseFirestore.getInstance();
  }

  public DocumentReference getUser(String userId) {
    return getCollections(Keys.CollectionName.PUBLIC_USER.KEY).document(userId);
  }

  public DocumentReference getPost(PostId postId) {
    return getCollections(Keys.CollectionName.POSTS.KEY).document(postId.toString());
  }

  public CollectionReference getCollections(String collection) {
    return database.collection(collection);
  }

  public void getAllOtherUsers(String currentUserId, UserAdapterCallback callback) {
    CollectionReference collectionReference = getCollections(Keys.CollectionName.PUBLIC_USER.KEY);
    collectionReference
        .get()
        .addOnCompleteListener(
            task -> onUsersQueryComplete(task, x -> !x.equals(currentUserId), callback));
  }

  public void getBlockedUsers(Set<UserId> blockedUserId, UserAdapterCallback callback) {
    CollectionReference collectionReference = getCollections(Keys.CollectionName.PUBLIC_USER.KEY);
    collectionReference
        .get()
        .addOnCompleteListener(
            task ->
                onUsersQueryComplete(task, x -> blockedUserId.contains(new UserId(x)), callback));
  }

  private void onUsersQueryComplete(
      Task<QuerySnapshot> task, Predicate<String> predicate, UserAdapterCallback callback) {
    if (task.isSuccessful() && task.getResult() != null) {
      List<User> users = new ArrayList<>();
      for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
        if (predicate.test(queryDocumentSnapshot.getId())) {
          User user = SnapshotParser.parseUser(queryDocumentSnapshot);
          users.add(user);
        }
      }
      callback.onUsersLoaded(users);
    }
  }

  //  public void loadBlockUsers(UserId userId, BlockedUsersCallback blockedUsersCallback) {
  //    DocumentReference documentReference = getUser(userId.toString());
  //    documentReference
  //        .get()
  //        .addOnSuccessListener(
  //            documentSnapshot -> onSuccessBlockUsers(documentSnapshot, blockedUsersCallback));
  //  }
  //
  //  private void onSuccessBlockUsers(
  //      DocumentSnapshot documentSnapshot, BlockedUsersCallback blockedUsersCallback) {
  //    List<String> blocked = new ArrayList<>();
  //    if (documentSnapshot.get(Keys.UserDocumentName.BLOCK.KEY) != null) {
  //      blocked.addAll((List<String>) documentSnapshot.get(Keys.UserDocumentName.BLOCK.KEY));
  //    }
  //    blockedUsersCallback.blockedUsersLoaded(blocked);
  //  }

  public void loadBookmark(UserId currentUserId, BookmarkCallback bookmarkCallback) {
    DocumentReference documentReference = getUser(currentUserId.toString());
    documentReference
        .get()
        .addOnSuccessListener(
            documentSnapshot -> onSuccessBookmark(documentSnapshot, bookmarkCallback));
  }

  private void onSuccessBookmark(
      DocumentSnapshot documentSnapshot, BookmarkCallback bookmarkCallback) {
    List<String> bookmark = new ArrayList<>();
    if (documentSnapshot.get(Keys.UserDocumentName.BOOKMARK.KEY) != null) {
      bookmark.addAll((List<String>) documentSnapshot.get(Keys.UserDocumentName.BOOKMARK.KEY));
    }
    bookmarkCallback.bookmarkLoad(bookmark);
  }

  //  public void loadFollowUsers(UserId currentUserId, FollowedUsersCallback followedUsersCallback)
  // {
  //    DocumentReference documentReference = getUser(currentUserId.toString());
  //    documentReference
  //            .get()
  //            .addOnSuccessListener(
  //                    documentSnapshot -> onSuccessFollowedUsers(documentSnapshot,
  // followedUsersCallback));
  //  }
  //
  //  private void onSuccessFollowedUsers(
  //          DocumentSnapshot documentSnapshot, FollowedUsersCallback followedUsersCallback) {
  //    List<String> followed = new ArrayList<>();
  //    if (documentSnapshot.get(Keys.UserDocumentName.FOLLOW.KEY) != null) {
  //      followed.addAll((List<String>) documentSnapshot.get(Keys.UserDocumentName.FOLLOW.KEY));
  //    }
  //    followedUsersCallback.followedUserLoad(followed);
  //  }

  public void loadUsers(String key, UserId currentUserId, UserCallback userCallback) {
    DocumentReference documentReference = getUser(currentUserId.toString());
    documentReference
        .get()
        .addOnSuccessListener(
            documentSnapshot -> onSuccessUsers(key, documentSnapshot, userCallback));
  }

  private void onSuccessUsers(
      String key, DocumentSnapshot documentSnapshot, UserCallback userCallback) {
    List<String> users = new ArrayList<>();
    if (documentSnapshot.get(key) != null) {
      users.addAll((List<String>) documentSnapshot.get(key));
    }
    userCallback.userLoad(users);
  }
}
