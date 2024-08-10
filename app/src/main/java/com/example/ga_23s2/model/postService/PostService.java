package com.example.ga_23s2.model.postService;

import com.example.ga_23s2.model.post.Post;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PostService {
  FilterService filtering;
  SortService sorting;

  public PostService() {
    this.filtering = new FilterService();
    this.sorting = new SortService();
  }

  public List<Post> get(List<Post> posts) {
    if (posts == null) return new ArrayList<>();
    List<Post> result =
        posts.stream().filter(filtering.get()).sorted(sorting.get()).collect(Collectors.toList());
    if (!result.isEmpty()) filtering.pushStage();
    return result;
  }

  public void addPredicate(Predicate<Post> p) {
    filtering.addToStage(p);
  }

  public void pushStagedPredicate() {
    filtering.pushStage();
  }

  public void addPriorityComparator(Comparator<Post> comp) {
    sorting.addPriority(comp);
  }

  public void addInferredComparator(Comparator<Post> comp) {
    sorting.addInferred(comp);
  }

  public void addAnyWhereContains(String s) {
    filtering.addToStage(filtering.anywhereContains(s));
  }
}
