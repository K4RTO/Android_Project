package com.example.ga_23s2.view.p2pFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.SnapshotParser;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.p2p.UserListener;
import com.example.ga_23s2.databinding.FragmentChatBinding;
import com.example.ga_23s2.model.User;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan Xiao
 */
public class ChatFragment extends Fragment implements UserListener {
  private FragmentChatBinding binding;
  private Session session;
  private UserId currentId;
  UserAdapter userAdapter;
  private EventListener<QuerySnapshot> eventListener;

  @SuppressLint("MissingInflatedId")
  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentChatBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    session = Session.getInstance();
    currentId = session.getCurrentUserId();
  }

  @Override
  public void onResume() {
    super.onResume();
    getUsers();
  }

  private void getUsers() {
    DataAccess dataAccess = new DataAccess();
    dataAccess.getAllOtherUsers(
        currentId.toString(),
        users -> {
          if (users.size() > 0) {
            userAdapter = new UserAdapter(getContext(), users, this);
            binding.recycleViewChat.setAdapter(userAdapter);

            eventListener = createEventListener(new ArrayList<>());
            session.getUserCollection().addSnapshotListener(eventListener);

          } else {
            Toast.makeText(getContext(), "No users", Toast.LENGTH_SHORT).show();
          }
        });
  }

  public EventListener<QuerySnapshot> createEventListener(List<User> users) {
    return (value, error) -> {
      if (error != null) return;
      if (value != null) {
        handleDocumentChanges(value.getDocumentChanges(), users);
      }
    };
  }

  private void handleDocumentChanges(List<DocumentChange> documentChanges, List<User> users) {
    int count = users.size();
    for (DocumentChange documentChange : documentChanges) {
      if (documentChange.getType() == DocumentChange.Type.ADDED) {
        User user = SnapshotParser.parseUser(documentChange.getDocument());
        users.add(user);
      }
    }
    if (count == 0) {
      userAdapter.notifyDataSetChanged();
    } else {
      userAdapter.notifyItemRangeInserted(users.size(), users.size());
      binding.recycleViewChat.smoothScrollToPosition(users.size() - 1);
    }
  }

  @Override
  public void onUserClicked(User user) {
    Intent intent = new Intent(getContext(), ChatActivity.class);
    String keyId = getResources().getString(R.string.intent_extra_userId);
    String keyImg = getResources().getString(R.string.intent_extra_profileImg);
    intent.putExtra(keyId, user.getUserId());
    intent.putExtra(keyImg, user.getProfileImg());
    startActivity(intent);
  }
}
