package com.example.ga_23s2.view.p2pFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.p2p.UserListener;
import com.example.ga_23s2.model.User;
import java.util.List;

/**
 * @author Yifan Xiao
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>
    implements UserListener {

  Context context;
  List<User> userList;
  UserListener userListener;
  LayoutInflater layoutInflater;

  public UserAdapter(Context context, List<User> userList, UserListener userListener) {
    this.context = context;
    this.userList = userList;
    this.userListener = userListener;
    layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.user_list_cardview, parent, false);
    return new UserHolder(view, userListener);
  }

  @Override
  public void onBindViewHolder(@NonNull UserHolder holder, int position) {
    holder.setUserData(userList.get(position));
  }

  @Override
  public int getItemCount() {
    return userList.size();
  }

  @Override
  public void onUserClicked(User user) {}

  public class UserHolder extends RecyclerView.ViewHolder {
    TextView username;
    ImageView profileImg;

    public UserHolder(@NonNull View itemView, UserListener userListener) {
      super(itemView);
      username = itemView.findViewById(R.id.txtContacts);
      profileImg = itemView.findViewById(R.id.imgContacts);
    }

    void setUserData(User user) {
      username.setText(user.getName());
      profileImg.setImageBitmap(getUserImage(user.getProfileImg()));
      itemView.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (userListener != null) {
                userListener.onUserClicked(user);
              }
            }
          });
    }
  }

  private Bitmap getUserImage(String encodedImg) {
    byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
  }
}
