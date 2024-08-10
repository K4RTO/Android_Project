package com.example.ga_23s2.view.DataService;

import android.content.Context;
import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.model.tree.RBTree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataAccessService {
  List<GameEntry> listOfGames;
  Context context;
  private final String GAME_FILENAME = "games.txt";
  private final String GAME_FILE_DELIMITER = "%%%";

  public DataAccessService(Context context) {
    this.context = context;
    listOfGames = new ArrayList<>();
    readGameData();
  }

  public void readGameData() {
    try {
      InputStream fis = context.getAssets().open(GAME_FILENAME);
      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
      String line;
      RBTree<String, GameEntry> gameTree = new RBTree<>();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
      while ((line = reader.readLine()) != null) {
        String[] sub = line.split(GAME_FILE_DELIMITER);
        GameEntry gameEntry = new GameEntry();
        // "name"         "platforms"    "price"        "release_date" "RAM"          "required_age"
        gameEntry
            .addName(sub[0])
            .addPlatforms(sub[1])
            .addPrice(Double.valueOf(sub[2]))
            .addReleaseDate(formatter.parse(sub[3], new ParsePosition(0)))
            .addRAM(sub[4])
            .addRequireAge(Integer.parseInt(sub[5]));
        gameTree.insert(gameEntry.getName(), gameEntry);
      }
      DataStorageService storageService = DataStorageService.getInstance();
      storageService.setGames(gameTree);
      gameTree.getList();
      gameTree.getReverseList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
