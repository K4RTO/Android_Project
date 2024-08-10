package com.example.ga_23s2.view.accountFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.cloud.p2p.UserListener;
import com.example.ga_23s2.databinding.ActivityBlockedUsersBinding;
import com.example.ga_23s2.model.User;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.view.p2pFragment.ChatActivity;
import com.example.ga_23s2.view.p2pFragment.UserAdapter;
import java.util.Set;
import java.util.stream.Collectors;

public class BlockedUsersActivity extends AppCompatActivity implements UserListener {

  ActivityBlockedUsersBinding binding;
  UserId currentId;
  DataAccess dataAccess;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityBlockedUsersBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    String keyId = getResources().getString(R.string.intent_extra_userId);
    currentId = new UserId(getIntent().getStringExtra(keyId));
    dataAccess = new DataAccess();

    getBlockedUsers();
    binding.header.headerMoreAction.setVisibility(View.INVISIBLE);
    binding.header.txtHeader.setText("Blocked Users");
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());
  }

  private void getBlockedUsers() {
    dataAccess.loadUsers(
        Keys.UserDocumentName.BLOCK.KEY(),
        currentId,
        blocked -> {
          Set<UserId> blockedUsers = blocked.stream().map(UserId::new).collect(Collectors.toSet());
          dataAccess.getBlockedUsers(
              blockedUsers,
              users -> {
                if (users.size() > 0) {
                  UserAdapter userAdapter = new UserAdapter(getApplicationContext(), users, this);
                  binding.recycleViewBlockedUser.setAdapter(userAdapter);
                } else {
                  Toast.makeText(getApplicationContext(), "No users", Toast.LENGTH_SHORT).show();
                }
              });
        });
  }

  @Override
  public void onUserClicked(User user) {
    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
    String keyId = getResources().getString(R.string.intent_extra_userId);
    String keyImg = getResources().getString(R.string.intent_extra_profileImg);
    intent.putExtra(keyId, user.getUserId());
    intent.putExtra(keyImg, user.getProfileImg());
    startActivity(intent);
  }
}
