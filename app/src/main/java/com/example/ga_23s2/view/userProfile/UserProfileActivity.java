package com.example.ga_23s2.view.userProfile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.ActivityUserProfileBinding;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.userActivity.BlockUserService;
import com.example.ga_23s2.view.posts.PostAdapter;
import com.example.ga_23s2.view.posts.PostLoader;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Yifan Xiao
 */
public class UserProfileActivity extends AppCompatActivity {

  ActivityUserProfileBinding binding;
  BlockUserService blockedUsers;
  Set<UserId> followedUsers = new HashSet<>();
  UserId otherUserId;
  UserId currentUserId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    currentUserId = Session.getInstance().getCurrentUserId();
    String keyId = getResources().getString(R.string.intent_extra_userId);
    otherUserId = new UserId(getIntent().getStringExtra(keyId));

    binding.header.headerMoreAction.setVisibility(View.INVISIBLE);
    binding.header.txtHeader.setText("User Profile");

    loadBlockAndFollow();
    loadUserDetails();
    loadUserPosts();
    setListeners();
    //    getToken();
  }

  private void loadUserDetails() {
    DataAccess dataAccess = new DataAccess();
    DocumentReference documentReference = dataAccess.getUser(otherUserId.toString());
    documentReference
        .get()
        .addOnSuccessListener(
            d -> {
              binding.txtUserName.setText(d.getString(Keys.UserDocumentName.ACCOUNT.KEY()));
              String imgString = d.getString(Keys.UserDocumentName.PROFILE_IMG.KEY());
              if (imgString == null) imgString = getString(R.string.DEFAULT_IMG);
              Bitmap bitmap = ProcessImage.decodeImage(imgString);
              binding.profileImg.setImageBitmap(bitmap);
            });
  }

  private void loadUserPosts() {
    PostAdapter postAdapter = new PostAdapter(getApplicationContext());
    binding.recyclerWorks.setAdapter(postAdapter);
    Predicate<QueryDocumentSnapshot> postByOther =
        x -> {
          String authorId = x.getString(Keys.PostDocumentName.USER_ID.KEY());
          return otherUserId.toString().equals(authorId);
        };
    PostLoader postLoader = new PostLoader(getApplicationContext(), postByOther);
    postLoader.loadUserPosts(postAdapter::filterList);
  }

  private void loadBlockAndFollow() {
    DataAccess dataAccess = new DataAccess();
    dataAccess.loadUsers(
        Keys.UserDocumentName.BLOCK.KEY(),
        currentUserId,
        users -> {
          blockedUsers = new BlockUserService(UserId.convertListToSet(users));
          if (blockedUsers.isBlocked(otherUserId)) {
            binding.btnBlock.setText("Blocked");
          }
        });
    dataAccess.loadUsers(
        Keys.UserDocumentName.FOLLOW.KEY(),
        currentUserId,
        users -> {
          Set<UserId> userIds = UserId.convertListToSet(users);
          if (userIds.contains(otherUserId)) {
            binding.btnFollow.setText("Followed");
          }
        });
  }

  private void setListeners() {
    binding.btnBlock.setOnClickListener(v -> blockOrUnblock());
    binding.btnFollow.setOnClickListener(v -> followOrUnfollow());
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());
  }

  private void blockOrUnblock() {
    if (binding.btnBlock.getText().toString().equals("Blocked")) {
      removeUsers(Keys.UserDocumentName.BLOCK.KEY());
      binding.btnBlock.setText("Block");
    } else {
      addUsers(Keys.UserDocumentName.BLOCK.KEY());
      binding.btnBlock.setText("Blocked");
    }
  }

  private void followOrUnfollow() {
    if (binding.btnFollow.getText().toString().equals("Followed")) {
      removeUsers(Keys.UserDocumentName.FOLLOW.KEY());
      binding.btnFollow.setText("Follow");
    } else {
      addUsers(Keys.UserDocumentName.FOLLOW.KEY());
      binding.btnFollow.setText("Followed");
    }
  }

  private void addUsers(String key) {
    DataAccess dataAccess = new DataAccess();
    dataAccess.loadUsers(
        key,
        currentUserId,
        users -> {
          users.add(otherUserId.toString());
          dataAccess.getUser(currentUserId.toString()).update(key, users);
        });
  }

  private void removeUsers(String key) {
    DataAccess dataAccess = new DataAccess();
    dataAccess.loadUsers(
        key,
        currentUserId,
        users -> {
          users.remove(otherUserId.toString());
          dataAccess.getUser(currentUserId.toString()).update(key, users);
        });
  }
}
