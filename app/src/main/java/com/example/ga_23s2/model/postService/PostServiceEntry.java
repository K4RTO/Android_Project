package com.example.ga_23s2.model.postService;

import androidx.annotation.NonNull;
import com.example.ga_23s2.model.post.Post;
import java.util.Comparator;
import java.util.function.Predicate;

public class PostServiceEntry {
  boolean isPriority = false;
  Comparator<Post> comp = (o1, o2) -> 0;
  Predicate<Post> pred = x -> true;
  String tokenString;

  public PostServiceEntry() {}

  public void setPriority(boolean priority) {
    isPriority = priority;
  }

  public void setSortComparator(Comparator<Post> comp) {
    this.comp = comp;
  }

  public void reverseComparator() {
    if (this.comp == null) return;
    this.comp = this.comp.reversed();
  }

  public void setPredicate(Predicate<Post> pred) {
    this.pred = pred;
  }

  public void negatePredicate() {

    this.pred = this.pred.negate();
  }

  public boolean isPriority() {
    return isPriority;
  }

  public Comparator<Post> getComp() {
    return comp;
  }

  public Predicate<Post> getPred() {
    return pred;
  }

  public String getTokenString() {
    return tokenString;
  }

  public void setTokenString(String tokenString) {
    this.tokenString = tokenString;
  }

  @NonNull
  @Override
  public String toString() {
    //    StringBuilder builder = new StringBuilder();
    //    if (pred != null) builder.append("Filter: ").append(pred.toString()).append(". ");
    //    if (isPriority && comp != null) builder.append("Compared by: ").append(comp.toString());
    //    return builder.toString();
    return tokenString;
  }
}
