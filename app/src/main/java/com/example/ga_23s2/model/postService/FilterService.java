package com.example.ga_23s2.model.postService;

import com.example.ga_23s2.model.post.Post;
import java.util.function.Predicate;

public class FilterService {
  private Predicate<Post> base = x -> true;
  private Predicate<Post> staged = x -> true;

  public Predicate<Post> get() {
    return x -> base.and(staged).test(x);
  }

  public Predicate<Post> anywhereContains(String s) {
    return x ->
        titleContains(s).or(gameContains(s)).or(contentContains(s)).or(authorContents(s)).test(x);
  }

  public Predicate<Post> titleContains(String s) {
    return x -> x.getTitle().contains(s);
  }

  public Predicate<Post> gameContains(String s) {
    return x -> x.getGame().getName().contains(s);
  }

  public Predicate<Post> contentContains(String s) {
    return x -> x.getContents().contains(s);
  }

  public Predicate<Post> authorContents(String s) {
    return x -> x.getAuthor().contains(s);
  }

  public void addToStage(Predicate<Post> p) {
    staged = p;
  }

  public void removeStage() {
    staged = x -> true;
  }

  public void pushStage() {
    if (base == null) base = staged;
    else base = base.and(staged);
    removeStage();
  }
}
