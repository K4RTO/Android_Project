package com.example.ga_23s2.cloud.p2p;

import androidx.recyclerview.widget.RecyclerView;
import com.example.ga_23s2.cloud.SnapshotParser;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.model.p2p.ChatMessage;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Yifan Xiao
 */
public class ChatMsgHandler {
  private List<ChatMessage> chatMsgList;
  private RecyclerView.Adapter chatAdapter;
  private RecyclerView recycleViewChat;

  public ChatMsgHandler(
      List<ChatMessage> chatMsgList,
      RecyclerView.Adapter chatAdapter,
      RecyclerView recycleViewChat) {
    this.chatMsgList = chatMsgList;
    this.chatAdapter = chatAdapter;
    this.recycleViewChat = recycleViewChat;
  }

  public EventListener<QuerySnapshot> createEventListener() {
    return (value, error) -> {
      if (error != null) return;
      if (value != null) {
        handleDocumentChanges(value.getDocumentChanges());
      }
    };
  }

  private void handleDocumentChanges(List<DocumentChange> documentChanges) {
    int count = chatMsgList.size();
    for (DocumentChange documentChange : documentChanges) {
      if (documentChange.getType() == DocumentChange.Type.ADDED) {
        ChatMessage chatMessage = SnapshotParser.parseChat(documentChange.getDocument());
        chatMsgList.add(chatMessage);
      }
    }
    Collections.sort(chatMsgList, Comparator.comparing(o -> o.getDateObject()));
    if (count == 0) {
      chatAdapter.notifyDataSetChanged();
    } else {
      chatAdapter.notifyItemRangeInserted(chatMsgList.size(), chatMsgList.size());
      recycleViewChat.smoothScrollToPosition(chatMsgList.size() - 1);
    }
  }

  private ChatMessage createChatMsgFromDocument(DocumentSnapshot document) {
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setSenderId(document.getString(Keys.MessageDocumentName.SENDER_ID.KEY()));
    chatMessage.setReceiverId(document.getString(Keys.MessageDocumentName.RECEIVER_ID.KEY()));
    chatMessage.setMessage(document.getString(Keys.MessageDocumentName.MESSAGE.KEY()));
    chatMessage.setDateObject(document.getDate(Keys.MessageDocumentName.TIME_STAMP.KEY()));
    chatMessage.setDateTime(getDateTime(chatMessage.getDateObject()));
    return chatMessage;
  }

  private String getDateTime(Date date) {
    return new SimpleDateFormat("MM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
  }
}
