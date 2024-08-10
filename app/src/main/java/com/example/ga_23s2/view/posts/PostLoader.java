package com.example.ga_23s2.view.posts;

import android.content.Context;
import android.widget.Toast;
import com.example.ga_23s2.cloud.SnapshotParser;
import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.viewmodel.Session;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Yifan Xiao
 */
public class PostLoader {

  private final Context context;
  private final Predicate<QueryDocumentSnapshot> predicate;

  public PostLoader(Context context, Predicate<QueryDocumentSnapshot> predicate) {
    this.context = context;
    this.predicate = predicate;
  }

  public void loadUserPosts(LoadPostsCallBack callBack) {
    Task<QuerySnapshot> task = Session.getInstance().getPostsCollection().get();
    task.addOnSuccessListener(
        qSnapshots -> {
          List<Post> posts = new ArrayList<>();
          for (QueryDocumentSnapshot snapshot : qSnapshots) {
            if (predicate.test(snapshot)) {
              posts.add(SnapshotParser.parsePost(snapshot));
            }
          }
          callBack.onSuccess(posts);
        });
    task.addOnFailureListener(
        t -> Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show());
  }

  //  @Override
  //  public void onPostClicked(Post post) {
  //    Intent intent = new Intent(context, PostDetailActivity.class);
  //    intent.putExtra("postId", post.getPostId().getId());
  //    context.startActivity(intent);
  //  }
}
