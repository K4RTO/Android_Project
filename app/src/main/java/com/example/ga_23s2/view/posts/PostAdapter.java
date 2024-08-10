package com.example.ga_23s2.view.posts;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ga_23s2.R;
import com.example.ga_23s2.model.ProcessImage;
import com.example.ga_23s2.model.post.Post;
import java.util.List;

/**
 * @author Yifan Xiao
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

  List<Post> postList;
  LayoutInflater layoutInflater;
  private Context context;

  public PostAdapter(Context context) {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.post_cardview, parent, false);
    return new PostHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PostHolder holder, int position) {
    holder.setPostData(postList.get(position));
  }

  public void filterList(List<Post> filterList) {
    postList = filterList;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return postList == null ? 0 : postList.size();
  }

  public class PostHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView postDetail;
    ImageView img;

    public PostHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.gameName);
      postDetail = itemView.findViewById(R.id.postDetail);
      img = itemView.findViewById(R.id.cardView);
    }

    void setPostData(Post post) {
      title.setText(post.getTitle());
      String contents = post.getContents();
      String dots = (contents.length() > 140) ? "..." : "";
      postDetail.setText(contents.substring(0, Math.min(contents.length(), 140)).concat(dots));
      img.setImageBitmap(ProcessImage.decodeImage(post.getPostImg()));
      itemView.setOnClickListener(view -> onPostClicked(post));
    }

    public void onPostClicked(Post post) {
      Intent intent = new Intent(context, PostDetailActivity.class);
      intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
      intent.putExtra("POST", post);
      //      intent.putExtra("postId", post.getPostId().getId());
      context.startActivity(intent);
    }
  }
}
