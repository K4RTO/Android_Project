package com.example.ga_23s2.view.posts.addPost;

import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.view.DataService.DataStorageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
  private Map<String, GameEntry> gameEntryMap;

  public GameManager(AddPostActivity activity) {
    gameEntryMap = loadGameEntries(activity);
  }

  public Map<String, GameEntry> getAllGames() {
    return this.gameEntryMap;
  }

  public GameEntry getGameEntry(String gameName) {
    return gameEntryMap.get(gameName);
  }

  private Map<String, GameEntry> loadGameEntries(AddPostActivity activity) {
    Map<String, GameEntry> gameEntryMap = new HashMap<>();
    List<GameEntry> games = DataStorageService.getInstance().getGameList();
    if (games != null) {
      for (GameEntry game : games) {
        gameEntryMap.put(game.getName(), game);
      }
    }
    return gameEntryMap;
  }
}
