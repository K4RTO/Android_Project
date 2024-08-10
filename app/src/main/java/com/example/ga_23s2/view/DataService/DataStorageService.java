package com.example.ga_23s2.view.DataService;

import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.model.tree.RBTree;
import java.util.List;

public class DataStorageService {
  private static DataStorageService dataStorageService;
  private List<Post> posts;
  private RBTree<String, GameEntry> gameTree;

  private DataStorageService() {
    // load data to posts;
  }

  public static DataStorageService getInstance() {
    if (dataStorageService == null) dataStorageService = new DataStorageService();
    return dataStorageService;
  }

  public List<Post> getPosts() {
    return dataStorageService.posts;
  }

  public List<GameEntry> getGameList() {
    return gameTree.getList();
  }

  public void setPosts(List<Post> posts) {
    dataStorageService.posts = posts;
  }

  protected void setGames(RBTree<String, GameEntry> gameTree) {
    dataStorageService.gameTree = gameTree;
  }

  public GameEntry findGame(String gameName) {
    return gameTree.find(gameName);
  }
}
