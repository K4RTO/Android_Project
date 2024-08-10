package com.example.ga_23s2.view.fragmentHandler;

import com.example.ga_23s2.R;

public enum NavigationId {
  NAVI_SEARCH(R.id.navi_search),
  NAVI_CHAT(R.id.navi_chat),
  NAVI_ACCOUNT(R.id.navi_account);

  final Integer id;

  NavigationId(Integer id) {
    this.id = id;
  }
}
