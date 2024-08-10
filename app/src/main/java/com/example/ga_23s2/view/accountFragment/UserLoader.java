package com.example.ga_23s2.view.accountFragment;

import android.graphics.Bitmap;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.FragmentAccountBinding;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.DocumentReference;

public class UserLoader {

  private FragmentAccountBinding binding;
  private DocumentReference documentReference;

  public UserLoader(FragmentAccountBinding binding) {
    this.binding = binding;
    this.documentReference = Session.getInstance().getUserDocument();
  }

  public void loadUserNameAndEmail() {
    documentReference
        .get()
        .addOnSuccessListener(
            d -> {
              binding.txtUserName.setText(d.getString(Keys.UserDocumentName.ACCOUNT.KEY()));
              binding.txtEmail.setText(d.getString(Keys.UserDocumentName.EMAIL.KEY()));
            });
  }

  public void loadUserProfileImg() {
    documentReference
        .get()
        .addOnSuccessListener(
            documentSnapshot -> {
              String imgString;
              if (documentSnapshot.getString(Keys.UserDocumentName.PROFILE_IMG.KEY()) != null) {
                imgString = documentSnapshot.getString(Keys.UserDocumentName.PROFILE_IMG.KEY());
              } else {
                imgString = binding.getRoot().getContext().getString(R.string.DEFAULT_IMG);
              }
              Bitmap bitmap = ProcessImage.decodeImage(imgString);
              binding.profileImg.setImageBitmap(bitmap);
            });
  }
}
