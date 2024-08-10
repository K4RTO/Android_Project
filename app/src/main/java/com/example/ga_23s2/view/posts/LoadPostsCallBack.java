package com.example.ga_23s2.view.posts;

import com.example.ga_23s2.model.post.Post;
import java.util.List;

public interface LoadPostsCallBack {
  void onSuccess(List<Post> posts);
}
