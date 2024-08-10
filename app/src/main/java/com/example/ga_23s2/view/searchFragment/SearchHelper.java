package com.example.ga_23s2.view.searchFragment;

import androidx.appcompat.widget.SearchView;
import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.model.postService.Parser;
import com.example.ga_23s2.model.postService.PostService;
import com.example.ga_23s2.model.postService.PostServiceEntry;
import com.example.ga_23s2.view.DataService.DataStorageService;
import com.example.ga_23s2.view.posts.PostAdapter;
import java.util.List;

public class SearchHelper {
  String parsedString = "";
  List<Post> posts;
  PostService postService;
  PostAdapter postAdapter;

  public SearchHelper(PostAdapter postAdapter) {
    this.postService = new PostService();
    this.postAdapter = postAdapter;
  }

  public void renewPost() {
    posts = DataStorageService.getInstance().getPosts();
    postAdapter.filterList(posts);
  }

  public void onChange(String newText) {
    int n = parsedString.length();
    if (n > 0 && newText.length() > n) {
      if (!newText.substring(0, n).equals(parsedString)) {
        renewPost();
        postService = new PostService();
        updateAll(newText);
      } else updateOnSubstring(newText.substring(n));
    } else {
      updateAll(newText);
    }
    postAdapter.filterList(postService.get(posts));
  }

  public void updateAll(String s) {
    String[] splits = s.split(";");
    if (splits.length == 0) updateOnSubstring(s);
    else {
      StringBuilder queryBuilder = new StringBuilder();
      for (int i = 0; i < splits.length - 1; i++) {
        List<PostServiceEntry> postServiceEntries = updateOnSubstring(splits[i]);
        for (PostServiceEntry entry : postServiceEntries) {
          if (entry == null) continue;
          if (entry.isPriority()) postService.addPriorityComparator(entry.getComp());
          else {
            postService.addPredicate(entry.getPred());
            postService.addInferredComparator(entry.getComp());
            postService.pushStagedPredicate();
          }
          queryBuilder.append(splits[i]).append(";");
        }
      }
      parsedString = queryBuilder.toString();
      postAdapter.filterList(postService.get(DataStorageService.getInstance().getPosts()));
      updateOnSubstring(splits[splits.length - 1]); // not saving the result of last one
    }
  }

  public List<PostServiceEntry> updateOnSubstring(String s) {
    Parser parser = new Parser(s);
    return parser.parse();
  }

  public SearchView.OnQueryTextListener queryTextListener() {
    return new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (newText.length() == 0) renewPost();
        else onChange(newText);
        return true;
      }
    };
  }
}
