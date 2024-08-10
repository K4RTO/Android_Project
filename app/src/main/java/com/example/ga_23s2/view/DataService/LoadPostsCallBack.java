package com.example.ga_23s2.view.DataService;

import com.example.ga_23s2.cloud.dataAccess.AccessCallBack;
import com.example.ga_23s2.model.post.Post;
import java.util.List;

public class LoadPostsCallBack implements AccessCallBack {
  @Override
  public void onSuccess(Object o) {
    DataStorageService.getInstance().setPosts((List<Post>) o);
  }

  @Override
  public void onFailure() {}
}
