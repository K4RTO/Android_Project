package com.example.ga_23s2.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Huanjie Zhang
 */
public class UserId implements Serializable {
  final String userId;

  public UserId(String userId) {
    this.userId = userId;
  }

  @NonNull
  @Override
  public String toString() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserId otherUserId = (UserId) o;
    return Objects.equals(userId, otherUserId.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  public static Set<UserId> convertListToSet(List<String> stringList) {
    Set<UserId> userSet = new HashSet<>();

    for (String str : stringList) {
      userSet.add(new UserId(str));
    }

    return userSet;
  }
}
