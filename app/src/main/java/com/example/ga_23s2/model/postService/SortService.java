package com.example.ga_23s2.model.postService;

import com.example.ga_23s2.model.post.Post;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class SortService {
  private Deque<Comparator<Post>> inferredComp = new ArrayDeque<>();
  private Deque<Comparator<Post>> priorityComp = new ArrayDeque<>();

  public void addInferred(Comparator<Post> comp) {
    inferredComp.add(comp);
  }

  public void addPriority(Comparator<Post> comp) {
    priorityComp.add(comp);
  }

  public Comparator<Post> get() {
    return (o1, o2) -> {
      while (!priorityComp.isEmpty()) {
        int result = priorityComp.pollFirst().compare(o1, o2);
        if (result != 0) return result;
      }
      while (!inferredComp.isEmpty()) {
        int result = inferredComp.pollFirst().compare(o1, o2);
        if (result != 0) return result;
      }
      return defaultComparator.compare(o1, o2);
    };
  }

  public Comparator<Post> defaultComparator =
      new Comparator<Post>() {

        @Override
        public int compare(Post o1, Post o2) {
          List<Comparator<Post>> comparators = new ArrayList<>(4);
          comparators.add(latestModifiedDate.reversed()); // want larger timestamp
          comparators.add(alphabeticalTitle);
          comparators.add(alphabeticalAuthor);
          comparators.add(orderById);
          for (Comparator<Post> comparator : comparators) {
            int result = comparator.compare(o1, o2);
            if (result != 0) return result;
          }
          return 0;
        }
      };

  private final Comparator<Post> latestModifiedDate = Comparator.comparing(Post::getModifiedDate);
  private final Comparator<Post> alphabeticalTitle = Comparator.comparing(Post::getTitle);
  private final Comparator<Post> alphabeticalAuthor = Comparator.comparing(Post::getAuthor);
  private final Comparator<Post> orderById = Comparator.comparing(o -> o.getPostId().toString());
}
