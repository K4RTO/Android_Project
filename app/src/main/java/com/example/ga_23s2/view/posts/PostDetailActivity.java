package com.example.ga_23s2.view.posts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.ActivityPostDetailsBinding;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.model.post.PostId;
import com.example.ga_23s2.model.userActivity.BookmarkService;
import com.example.ga_23s2.view.DataService.DataStorageService;
import com.example.ga_23s2.view.posts.addPost.AddPostActivity;
import com.example.ga_23s2.view.userProfile.UserProfileActivity;
import com.example.ga_23s2.viewmodel.Session;
import java.util.ArrayList;
import java.util.List;

public class PostDetailActivity extends AppCompatActivity {

  ActivityPostDetailsBinding binding;
  Post post;
  DataAccess dataAccess;
  UserId userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    post = getIntent().getSerializableExtra("POST", Post.class);

    dataAccess = new DataAccess();
    userId = Session.getInstance().getCurrentUserId();

    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());
    binding.header.headerMoreAction.setOnClickListener(v -> userProfile());
    binding.icBookmark.setOnClickListener(v -> checkBookmark());
    loadPostDetail();
  }

  private void loadPostDetail() {
    binding.postDetailContent.setText(post.getContents());
    binding.txtPostTitle.setText(post.getTitle());
    binding.txtBookmarkCount.setText(String.valueOf(post.getBookmarked()));
    String imgString = getString(R.string.DEFAULT_IMG);
    Bitmap bitmap = ProcessImage.decodeImage(imgString);
    binding.imgPost.setImageBitmap(bitmap);
    loadGameEntry(post.getGame().getName());
    dataAccess
        .getUser(post.getUserId().toString())
        .get()
        .addOnSuccessListener(
            d -> {
              binding.header.txtHeader.setText(d.getString(Keys.UserDocumentName.ACCOUNT.KEY()));
            });
  }

  private void loadGameEntry(String gameName) {
    List<GameEntry> games = DataStorageService.getInstance().getGameList();
    List<String> gameDetails = new ArrayList<>();
    for (GameEntry game : games) {
      if (game.getName().equals(gameName)) {
        gameDetails.add("Game: " + gameName);
        gameDetails.add("Platform: " + game.getPlatforms());
        gameDetails.add("Price: " + game.getPrice().toString());
        gameDetails.add("Released date: " + game.getRelease_date().toString());
        gameDetails.add("RAM: " + game.getRAM());
        gameDetails.add("Required age: " + game.getRequired_age());
      }
    }
    Adapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gameDetails);
    binding.listViewGameEntry.setAdapter((ListAdapter) adapter);
    binding.listViewGameEntry.setExpanded(true);
  }

  private void userProfile() {
    dataAccess
        .getCollections(Keys.CollectionName.POSTS.KEY)
        .document(post.getPostId().toString())
        .get()
        .addOnSuccessListener(
            documentSnapshot -> {
              if (documentSnapshot
                  .getString(Keys.PostDocumentName.USER_ID.KEY())
                  .equals(userId.toString())) {
                Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
                String keyId = getResources().getString(R.string.intent_extra_postId);
                intent.putExtra(keyId, post.getPostId().toString());
                startActivity(intent);
              } else {
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                String keyId = getResources().getString(R.string.intent_extra_userId);
                intent.putExtra(keyId, post.getUserId().toString());
                startActivity(intent);
              }
            });
  }

  private void checkBookmark() {
    if (userId == null) {
      Toast.makeText(getApplicationContext(), "Please Log-in", Toast.LENGTH_SHORT).show();
    } else {
      dataAccess.loadBookmark(
          userId,
          bookmark -> {
            BookmarkService bookmarkService =
                new BookmarkService(PostId.convertListToSet(bookmark));
            if (bookmarkService.isBookmarked(post.getPostId())) {
              unBookmark();
            } else {
              addBookmark();
            }
          });
    }
  }

  private void addBookmark() {
    dataAccess.loadBookmark(
        userId,
        bookmark -> {
          bookmark.add(post.getPostId().toString());
          dataAccess.getUser(userId.toString()).update("bookmark", bookmark);
          increaseBookmarkCount();
        });
  }

  private void unBookmark() {
    dataAccess.loadBookmark(
        userId,
        bookmark -> {
          bookmark.remove(post.getPostId().toString());
          dataAccess.getUser(userId.toString()).update("bookmark", bookmark);
          decreaseBookmarkCount();
        });
  }

  private void increaseBookmarkCount() {
    dataAccess
        .getPost(post.getPostId())
        .get()
        .addOnSuccessListener(
            documentSnapshot -> {
              int bookmarkCount =
                  documentSnapshot.getLong(Keys.PostDocumentName.BOOKMARK.KEY()).intValue() + 1;
              dataAccess
                  .getPost(post.getPostId())
                  .update(Keys.PostDocumentName.BOOKMARK.KEY(), bookmarkCount);
              binding.txtBookmarkCount.setText(String.valueOf(bookmarkCount));
            });
  }

  private void decreaseBookmarkCount() {
    dataAccess
        .getPost(post.getPostId())
        .get()
        .addOnSuccessListener(
            documentSnapshot -> {
              int bookmarkCount =
                  documentSnapshot.getLong(Keys.PostDocumentName.BOOKMARK.KEY()).intValue() - 1;
              dataAccess
                  .getPost(post.getPostId())
                  .update(Keys.PostDocumentName.BOOKMARK.KEY(), bookmarkCount);
              binding.txtBookmarkCount.setText(String.valueOf(bookmarkCount));
            });
  }
}
