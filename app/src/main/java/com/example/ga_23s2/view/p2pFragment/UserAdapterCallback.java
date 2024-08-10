package com.example.ga_23s2.view.p2pFragment;

import com.example.ga_23s2.model.User;
import java.util.List;

/**
 * @author Yifan Xiao
 */
public interface UserAdapterCallback {
  void onUsersLoaded(List<User> users);
  // void onFailure(String message);
}
