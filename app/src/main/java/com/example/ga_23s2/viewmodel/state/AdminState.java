package com.example.ga_23s2.viewmodel.state;

import com.example.ga_23s2.model.UserId;

/**
 * Admin user gets more control, to be implemented in the future.
 *
 * @author Huanjie Zhang
 */
public class AdminState extends UserState {

  public AdminState(UserId currentUserId) {
    super(currentUserId);
  }
}
