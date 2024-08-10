package com.example.ga_23s2.model.userActivity;

import com.example.ga_23s2.model.UserId;
import java.util.Set;

/**
 * @author
 */
public class BlockUserService {

  Set<UserId> blockedUser;

  public BlockUserService(Set<UserId> blockedUser) {
    this.blockedUser = blockedUser;
  }

  public void blockUser(UserId currentUser, UserId otherUser) {}

  public boolean isBlocked(UserId otherUser) {
    if (blockedUser.contains(otherUser)) {
      return true;
    }
    return false;
  }
}
