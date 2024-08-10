package com.example.ga_23s2.view.p2pFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ga_23s2.R;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.p2p.ChatMessage;
import java.util.List;

/**
 * @author Yifan Xiao
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  List<ChatMessage> msgList;
  Bitmap receiverProfileImg;
  UserId senderId;
  Context context;
  LayoutInflater layoutInflater;

  public static final int VIEW_TYPE_SENT = 1;
  public static final int VIEW_TYPE_RECEIVED = 2;

  public ChatAdapter(
      Context context, List<ChatMessage> msgList, Bitmap receiverProfileImg, UserId senderId) {
    this.context = context;
    this.msgList = msgList;
    this.receiverProfileImg = receiverProfileImg;
    this.senderId = senderId;
    layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_SENT) {
      View view = layoutInflater.inflate(R.layout.sent_message_container, parent, false);
      return new SentMsgViewHolder(view);
    } else {
      View view = layoutInflater.inflate(R.layout.received_message_container, parent, false);
      return new ReceivedMsgViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == VIEW_TYPE_SENT) {
      ((SentMsgViewHolder) holder).setData(msgList.get(position));
    } else {
      ((ReceivedMsgViewHolder) holder).setData(msgList.get(position), receiverProfileImg);
    }
  }

  @Override
  public int getItemCount() {
    return msgList.size();
  }

  public int getItemViewType(int position) {
    if (msgList.get(position).senderId.equals(senderId.toString())) {
      return VIEW_TYPE_SENT;
    } else {
      return VIEW_TYPE_RECEIVED;
    }
  }

  class SentMsgViewHolder extends RecyclerView.ViewHolder {
    TextView textMsg;

    public SentMsgViewHolder(@NonNull View itemView) {
      super(itemView);
      textMsg = itemView.findViewById(R.id.txtSentMsg);
    }

    void setData(ChatMessage chatMessage) {
      textMsg.setText(chatMessage.message);
    }
  }

  class ReceivedMsgViewHolder extends RecyclerView.ViewHolder {
    TextView textMsg;
    ImageView receiverImg;

    public ReceivedMsgViewHolder(@NonNull View itemView) {
      super(itemView);
      textMsg = itemView.findViewById(R.id.txtReceivedMsg);
      receiverImg = itemView.findViewById(R.id.profileImgChat);
    }

    void setData(ChatMessage chatMessage, Bitmap receiverImgBitmap) {
      textMsg.setText(chatMessage.message);
      receiverImg.setImageBitmap(receiverImgBitmap);
    }
  }
}
