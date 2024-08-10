package com.example.ga_23s2.view.accountFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.ActivityUpdataProfileBinding;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.UserId;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateProfileActivity extends AppCompatActivity {
  ActivityUpdataProfileBinding binding;
  DataAccess dataAccess;
  UserId userId;
  String encodeImg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityUpdataProfileBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    dataAccess = new DataAccess();
    userId =
        new UserId(
            getIntent().getStringExtra(getResources().getString(R.string.intent_extra_userId)));

    binding.header.headerMoreAction.setVisibility(View.INVISIBLE);
    binding.header.txtHeader.setText("Edit Profile");
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());

    loadUserDetails();

    binding.profileImg.setOnClickListener(v -> setImage());
    binding.btnSubmit.setOnClickListener(v -> submitChange());
  }

  private void submitChange() {
    String newName = binding.edtAccountName.getText().toString();
    dataAccess
        .getUser(userId.toString())
        .update(Keys.UserDocumentName.PROFILE_IMG.KEY(), encodeImg);
    dataAccess.getUser(userId.toString()).update(Keys.UserDocumentName.ACCOUNT.KEY(), newName);
    onBackPressed();
  }

  private void loadUserDetails() {
    dataAccess
        .getUser(userId.toString())
        .get()
        .addOnSuccessListener(
            d -> {
              binding.edtAccountName.setText(d.getString(Keys.UserDocumentName.ACCOUNT.KEY()));
              if (d.getString(Keys.UserDocumentName.PROFILE_IMG.KEY()) != null) {
                binding.profileImg.setImageBitmap(
                    ProcessImage.decodeImage(d.getString(Keys.UserDocumentName.PROFILE_IMG.KEY())));
              }
            });
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
                binding.profileImg.setImageBitmap(bitmap);
                encodeImg = ProcessImage.encodeImage(bitmap);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
            }
          });

  private void setImage() {
    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    chooseImg.launch(intent);
  }
}
