package com.example.ga_23s2.view.accountFragment;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.databinding.ActivityBookmarkBinding;
import com.example.ga_23s2.model.UserId;

public class BookmarkActivity extends AppCompatActivity {
  ActivityBookmarkBinding binding;
  DataAccess dataAccess;
  UserId userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityBookmarkBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.header.headerMoreAction.setVisibility(View.INVISIBLE);
    binding.header.txtHeader.setText("Bookmark");
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());

    dataAccess = new DataAccess();
    userId =
        new UserId(
            getIntent().getStringExtra(getResources().getString(R.string.intent_extra_userId)));

    loadBookmarkedPost();
  }

  private void loadBookmarkedPost() {
    dataAccess.loadBookmark(userId, bookmarks -> {});
  }
}
