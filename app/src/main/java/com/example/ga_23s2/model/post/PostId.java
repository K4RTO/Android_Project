package com.example.ga_23s2.model.post;

/**
 * @author Huanjie Zhang
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PostId implements Serializable {
  private String id;

  public PostId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostId postId = (PostId) o;
    return Objects.equals(id, postId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return id;
  }

  public static Set<PostId> convertListToSet(List<String> stringList) {
    Set<PostId> postSet = new HashSet<>();

    for (String str : stringList) {
      postSet.add(new PostId(str));
    }

    return postSet;
  }
}
