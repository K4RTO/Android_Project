package com.example.ga_23s2.view.p2pFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.cloud.p2p.ChatMsgHandler;
import com.example.ga_23s2.databinding.ActivityChatBinding;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.p2p.ChatMessage;
import com.example.ga_23s2.model.userActivity.BlockUserService;
import com.example.ga_23s2.view.userProfile.UserProfileActivity;
import com.example.ga_23s2.viewmodel.Session;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yifan Xiao
 */
public class ChatActivity extends AppCompatActivity {

  private ActivityChatBinding binding;
  private Session session;
  private FirebaseFirestore database;
  private DataAccess dataAccess;
  private UserId senderId;
  private UserId receiverId;
  private Bitmap receiverImgBitmap;
  private List<ChatMessage> chatMsgList;
  private ChatAdapter chatAdapter;
  private EventListener<QuerySnapshot> eventListener;

  @SuppressLint("RestrictedApi")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityChatBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    session = Session.getInstance();
    senderId = session.getCurrentUserId();

    database = FirebaseFirestore.getInstance();
    dataAccess = new DataAccess();
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadReceiverDetails();
    setListeners();
    init();
    listenMessages();
  }

  private void init() {
    chatMsgList = new ArrayList<>();
    chatAdapter = new ChatAdapter(ChatActivity.this, chatMsgList, receiverImgBitmap, senderId);
    binding.recycleViewChat.setAdapter(chatAdapter);
  }

  private void setListeners() {
    binding.header.headerBackArrow.setOnClickListener(v -> onBackPressed());
    binding.header.headerMoreAction.setOnClickListener(v -> openUserProfile());
    binding.btnSend.setOnClickListener(v -> sendMsg());
  }

  private void openUserProfile() {
    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
    String keyId = getResources().getString(R.string.intent_extra_userId);
    intent.putExtra(keyId, receiverId.toString());
    startActivity(intent);
    onPause();
  }

  private void sendMsg() {
    dataAccess.loadUsers(
        Keys.UserDocumentName.BLOCK.KEY(),
        receiverId,
        blocked -> {
          BlockUserService blockedUsers = new BlockUserService(UserId.convertListToSet(blocked));
          if (!blockedUsers.isBlocked(senderId)) {
            checkLocationAndSendMessage();
          } else {
            Toast.makeText(getApplicationContext(), "blocked", Toast.LENGTH_SHORT).show();
          }
        });
  }

  private void checkLocationAndSendMessage() {
    dataAccess
        .getUser(receiverId.toString())
        .get()
        .addOnSuccessListener(
            documentSnapshot -> {
              String location = documentSnapshot.getString(Keys.UserDocumentName.LOCATION.KEY());
              if (location == null) {
                Toast.makeText(getApplicationContext(), "missing location", Toast.LENGTH_SHORT)
                    .show();
              } else {
                dataAccess
                    .getUser(senderId.toString())
                    .get()
                    .addOnSuccessListener(
                        snapshot -> {
                          String snapshotLocation =
                              snapshot.getString(Keys.UserDocumentName.LOCATION.KEY());
                          if (snapshotLocation != null && snapshotLocation.equals(location)) {
                            addMsg();
                          } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "different location",
                                    Toast.LENGTH_SHORT)
                                .show();
                          }
                        });
              }
            });
  }

  private void addMsg() {
    Map<String, Object> message = new HashMap<>();
    message.put(Keys.MessageDocumentName.SENDER_ID.KEY(), senderId.toString());
    message.put(Keys.MessageDocumentName.RECEIVER_ID.KEY(), receiverId.toString());
    message.put(Keys.MessageDocumentName.MESSAGE.KEY(), binding.edtSendMsg.getText().toString());
    message.put(Keys.MessageDocumentName.TIME_STAMP.KEY(), new Date());
    database.collection(Keys.CollectionName.MESSAGES.KEY).add(message);
    binding.edtSendMsg.setText(null);
  }

  private void loadReceiverDetails() {
    String keyId = getResources().getString(R.string.intent_extra_userId);
    receiverId = new UserId(getIntent().getStringExtra(keyId));
    String keyImg = getResources().getString(R.string.intent_extra_profileImg);
    receiverImgBitmap = ProcessImage.decodeImage(getIntent().getStringExtra(keyImg));
    Task<DocumentSnapshot> task = session.getUserDocument(receiverId).get();
    task.addOnSuccessListener(
        documentSnapshot -> {
          binding.header.txtHeader.setText(
              documentSnapshot.getString(Keys.UserDocumentName.ACCOUNT.KEY()));
        });
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
      if (v instanceof androidx.appcompat.widget.AppCompatEditText) {
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

  private void listenMessages() {
    ChatMsgHandler chatMessages =
        new ChatMsgHandler(chatMsgList, chatAdapter, binding.recycleViewChat);
    eventListener = chatMessages.createEventListener();
    if (senderId != null && receiverId != null) {
      session
          .getChatCollection()
          .whereEqualTo(Keys.MessageDocumentName.SENDER_ID.KEY(), senderId.toString())
          .whereEqualTo(Keys.MessageDocumentName.RECEIVER_ID.KEY(), receiverId.toString())
          .addSnapshotListener(eventListener);
      session
          .getChatCollection()
          .whereEqualTo(Keys.MessageDocumentName.SENDER_ID.KEY(), receiverId.toString())
          .whereEqualTo(Keys.MessageDocumentName.RECEIVER_ID.KEY(), senderId.toString())
          .addSnapshotListener(eventListener);
    }
  }
}
