package com.example.ga_23s2.view.accountFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.FragmentAccountBinding;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.view.LocationHelper;
import com.example.ga_23s2.view.MainActivity;
import com.example.ga_23s2.view.posts.PostAdapter;
import com.example.ga_23s2.view.posts.PostLoader;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * @author Yifan Xiao
 */
public class AccountFragment extends Fragment {

  private FragmentAccountBinding binding;
  private Session session;
  private UserId currentId;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentAccountBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    session = Session.getInstance();
    currentId = session.getCurrentUserId();

    setListener();
  }

  @Override
  public void onResume() {
    super.onResume();
    getLocation();
    loadUserDetails();
    getPosts();
  }

  private void setListener() {
    binding.btnSignOut.setOnClickListener(v -> signOut());
    binding.btnBlockUser.setOnClickListener(v -> getBlockedUsers());
    binding.btnBookmark.setOnClickListener(v -> getBookmarks());
    binding.btnSetProfile.setOnClickListener(v -> updateProfile());
  }

  private void updateProfile() {
    Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
    String key = getResources().getString(R.string.intent_extra_userId);
    intent.putExtra(key, currentId.toString());
    startActivity(intent);
  }

  private void getBookmarks() {
    Intent intent = new Intent(getContext(), BookmarkActivity.class);
    String key = getResources().getString(R.string.intent_extra_userId);
    intent.putExtra(key, currentId.toString());
    startActivity(intent);
  }

  private void getBlockedUsers() {
    Intent intent = new Intent(getContext(), BlockedUsersActivity.class);
    String key = getResources().getString(R.string.intent_extra_userId);
    intent.putExtra(key, currentId.toString());
    startActivity(intent);
  }

  private void loadUserDetails() {
    UserLoader userLoader = new UserLoader(binding);
    userLoader.loadUserNameAndEmail();
    userLoader.loadUserProfileImg();
  }

  private void getPosts() {
    UserId userId = session.getCurrentUserId();
    // filter current user's posts
    Predicate<QueryDocumentSnapshot> predicate =
        q -> q.getString(Keys.PostDocumentName.USER_ID.KEY()).equals(userId.toString());
    PostLoader postLoader = new PostLoader(getContext(), predicate);
    PostAdapter postAdapter = new PostAdapter(getContext());

    postLoader.loadUserPosts(postAdapter::filterList);
    postAdapter.notifyDataSetChanged();
    binding.posts.setAdapter(postAdapter);
  }

  private void getLocation() {
    LocationHelper locationHelper =
        new LocationHelper(getContext(), binding, (MainActivity) getActivity());
    locationHelper.checkLocationPermissionAndFetch();
  }

  private void signOut() {
    try {
      DataAccess dataAccess = new DataAccess();
      DocumentReference documentReference = dataAccess.getUser(currentId.toString());
      HashMap<String, Object> updates = new HashMap<>();
      String key = Session.getInstance().getStringResource(R.string.fcmToken);
      updates.put(key, FieldValue.delete());
      documentReference.update(updates);
    } catch (Exception e) {
    }
    session.logOut();
  }
}
