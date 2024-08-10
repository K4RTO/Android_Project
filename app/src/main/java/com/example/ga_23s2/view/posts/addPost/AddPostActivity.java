package com.example.ga_23s2.view.posts.addPost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.databinding.ActivityAddPostBinding;
import com.example.ga_23s2.model.ProcessImage;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddPostActivity extends AppCompatActivity {

  public ActivityAddPostBinding binding;
  private PostManager postManager;
  private GameManager gameManager;
  ArrayList<String> gameNames = new ArrayList<>();
  String encodeImg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAddPostBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    init();

    gameNames.addAll(gameManager.getAllGames().keySet());
    setAdapter();
  }

  private void init() {
    binding.header.headerMoreAction.setVisibility(View.INVISIBLE);
    binding.header.txtHeader.setText("Add Post");
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());
    binding.ImgLayout.setOnClickListener(view -> chooseImg());
    String keyId = getResources().getString(R.string.intent_extra_postId);
    if (getIntent().getStringExtra(keyId) == null) {
      binding.btnPost.setOnClickListener(
          v -> {
            postManager.submitPost();
            onBackPressed();
          });
    } else {
      String postId = getIntent().getStringExtra(keyId);
      binding.btnPost.setOnClickListener(
          v -> {
            postManager.editPost(postId);
            onBackPressed();
          });
    }

    postManager = new PostManager(this);
    gameManager = new GameManager(this);
  }

  private void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (imm != null) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      View v = getCurrentFocus();
      if (v != null && v instanceof androidx.appcompat.widget.AppCompatAutoCompleteTextView
          || v instanceof androidx.appcompat.widget.AppCompatEditText) {
        int scrcoords[] = new int[2];
        v.getLocationOnScreen(scrcoords);
        float x = event.getRawX() + v.getLeft() - scrcoords[0];
        float y = event.getRawY() + v.getTop() - scrcoords[1];
        if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
          hideKeyboard();
      }
    }
    return super.dispatchTouchEvent(event);
  }

  private void setAdapter() {
    ArrayAdapter<String> adapter =
        new ContainsArrayAdapter(
            getApplicationContext(), android.R.layout.select_dialog_item, gameNames);

    binding.autoCompleteGameName.setThreshold(1);
    binding.autoCompleteGameName.setAdapter(adapter);
  }

  private final ActivityResultLauncher<Intent> chooseImg =
      registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            if (result.getResultCode() == RESULT_OK) {
              Uri imageUri = result.getData().getData();
              try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                binding.imgPost.setImageBitmap(bitmap);
                binding.txtAddImg.setVisibility(View.GONE);
                encodeImg = ProcessImage.encodeImage(bitmap);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
            }
          });

  private void chooseImg() {
    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    chooseImg.launch(intent);
  }

  private class ContainsArrayAdapter extends ArrayAdapter<String> {

    private List<String> originalList;
    private List<String> filteredList;

    public ContainsArrayAdapter(
        @NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
      super(context, resource, objects);
      this.originalList = new ArrayList<>(objects);
      this.filteredList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public Filter getFilter() {
      return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
          FilterResults filterResults = new FilterResults();
          if (charSequence == null || charSequence.length() == 0) {
            filterResults.values = originalList;
            filterResults.count = originalList.size();
          } else {
            List<String> tempList = new ArrayList<>();
            for (String item : originalList) {
              if (item.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                tempList.add(item);
              }
            }
            filterResults.values = tempList;
            filterResults.count = tempList.size();
          }
          return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
          filteredList = (List<String>) filterResults.values;
          clear();
          addAll(filteredList);
          notifyDataSetChanged();
        }
      };
    }
  }
}
