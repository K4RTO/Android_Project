package com.example.ga_23s2.view.accountFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ga_23s2.R;
import com.example.ga_23s2.model.post.PostId;
import java.util.HashSet;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder> {

  Context context;
  HashSet<PostId> bookmarked;
  LayoutInflater layoutInflater;

  public BookmarkAdapter(Context context, HashSet<PostId> bookmarked) {
    this.context = context;
    this.bookmarked = bookmarked;
    layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public BookmarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.post_cardview, parent, false);
    return new BookmarkHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BookmarkHolder holder, int position) {
    // Set data to your views here
    holder.img.setImageResource(R.drawable.ic_launcher_background);
  }

  @Override
  public int getItemCount() {
    return bookmarked.size();
  }

  public static class BookmarkHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView postDetail;
    ImageView img;

    public BookmarkHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.gameName);
      postDetail = itemView.findViewById(R.id.postDetail);
      //      img = itemView.findViewById(R.id.img);
    }
  }
}
