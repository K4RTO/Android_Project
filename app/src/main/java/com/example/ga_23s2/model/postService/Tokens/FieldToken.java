package com.example.ga_23s2.model.postService.Tokens;

import com.example.ga_23s2.model.post.Post;
import java.util.function.Function;

public enum FieldToken implements Token {
  ANY_FILED(null),
  TITLE(Post::getTitle),
  AUTHOR(Post::getAuthor),
  CONTENTS(Post::getContents),
  GAME_NAME(post -> post.getGame().getName()),
  PLATFORM(post -> post.getGame().getPlatforms()),
  DATE_CREATED(null),
  DATE_MODIFIED(null),
  BOOKMARK_NUMBER(null);

  final Function<Post, String> consumer;

  FieldToken(Function<Post, String> consumer) {
    this.consumer = consumer;
  }

  public Function<Post, String> getConsumer() {
    return consumer;
  }
}
