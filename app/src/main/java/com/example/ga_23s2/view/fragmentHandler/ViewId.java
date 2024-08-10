package com.example.ga_23s2.view.fragmentHandler;

import com.example.ga_23s2.R;

public enum ViewId {
  AUTHENTICATION(R.layout.fragment_authentication),
  REQUIRE_LOGIN(R.layout.fragment_require_login),
  ACCOUNT(R.layout.fragment_account),
  CHAT(R.layout.fragment_chat),
  SEARCH(R.layout.fragment_search);

  final Integer id;

  ViewId(Integer id) {
    this.id = id;
  }
}
