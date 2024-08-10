package com.example.ga_23s2.view.posts.addPost;

import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.widget.Toast;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.cloud.dataAccess.PublicReference;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.model.post.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yifan Xiao
 */
public class PostManager {
  private FirebaseFirestore database;
  private PublicReference publicReference;
  private UserId userId;
  private GameManager gameManager;
  private AddPostActivity activity;

  public PostManager(AddPostActivity activity) {
    this.activity = activity;
    database = FirebaseFirestore.getInstance();
    publicReference = new PublicReference();
    userId = new UserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
    gameManager = new GameManager(activity);
  }

  public void submitPost() {
    if (isContentEmpty()) {
      Toast.makeText(activity, "Can't submit empty post", Toast.LENGTH_SHORT).show();
      return;
    }
    Post post = createNewPost();
    Map<String, Object> newPost = new HashMap<>();
    newPost.put(Keys.PostDocumentName.USER_ID.KEY(), post.getUserId().toString());
    newPost.put(Keys.PostDocumentName.TITLE.KEY(), post.getTitle());
    newPost.put(Keys.PostDocumentName.GAME_NAME.KEY(), post.getGame().getName());
    newPost.put(Keys.PostDocumentName.CONTENTS.KEY(), post.getContents());
    newPost.put(Keys.PostDocumentName.POST_DATE.KEY(), post.getDateOfCreation());
    newPost.put(Keys.PostDocumentName.BOOKMARK.KEY(), post.getBookmarked());
    newPost.put(Keys.PostDocumentName.POST_IMG.KEY(), post.getPostImg());
    database.collection(Keys.CollectionName.POSTS.KEY).add(newPost);
  }

  public void editPost(String postId) {
    if (isContentEmpty()) {
      Toast.makeText(activity, "Can't submit empty post", Toast.LENGTH_SHORT).show();
      return;
    }
    Post post = createNewPost();
    Map<String, Object> newPost = new HashMap<>();
    newPost.put(Keys.PostDocumentName.USER_ID.KEY(), post.getUserId().toString());
    newPost.put(Keys.PostDocumentName.TITLE.KEY(), post.getTitle());
    newPost.put(Keys.PostDocumentName.GAME_NAME.KEY(), post.getGame().getName());
    newPost.put(Keys.PostDocumentName.CONTENTS.KEY(), post.getContents());
    newPost.put(Keys.PostDocumentName.POST_DATE.KEY(), post.getDateOfCreation());
    newPost.put(Keys.PostDocumentName.BOOKMARK.KEY(), post.getBookmarked());
    newPost.put(Keys.PostDocumentName.POST_IMG.KEY(), post.getPostImg());
    database.collection(Keys.CollectionName.POSTS.KEY).document(postId).set(newPost);
  }

  private Post createNewPost() {
    String gameName = activity.binding.autoCompleteGameName.getText().toString();
    GameEntry gameEntry = gameManager.getGameEntry(gameName);
    Post post = new Post(userId, new Date());
    post.setTitle(activity.binding.edtTitle.getText().toString());
    post.setContents(activity.binding.edtPostContent.getText().toString());
    post.setPostImg(
        ProcessImage.encodeImage(
            ((BitmapDrawable) activity.binding.imgPost.getDrawable()).getBitmap()));
    post.setGame(gameEntry);
    post.setBookmarked(0);
    return post;
  }

  private boolean isContentEmpty() {
    return TextUtils.isEmpty(activity.binding.edtTitle.getText().toString().trim())
        || TextUtils.isEmpty(activity.binding.edtPostContent.getText().toString().trim());
  }
}
