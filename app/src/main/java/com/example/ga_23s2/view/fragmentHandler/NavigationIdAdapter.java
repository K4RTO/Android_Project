package com.example.ga_23s2.view.fragmentHandler;

import com.example.ga_23s2.R;
import java.util.HashMap;
import java.util.Map;

public class NavigationIdAdapter {
  public static Map<Integer, NavigationId> map = new HashMap<>();

  static {
    map.put(R.id.navi_search, NavigationId.NAVI_SEARCH);
    map.put(R.id.navi_chat, NavigationId.NAVI_CHAT);
    map.put(R.id.navi_account, NavigationId.NAVI_ACCOUNT);
  }

  public static NavigationId getNavigationId(int id) {
    return map.getOrDefault(id, NavigationId.NAVI_SEARCH);
  }
}
