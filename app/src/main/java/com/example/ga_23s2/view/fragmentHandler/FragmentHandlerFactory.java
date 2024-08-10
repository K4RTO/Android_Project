package com.example.ga_23s2.view.fragmentHandler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.ga_23s2.view.accountFragment.AccountFragment;
import com.example.ga_23s2.view.accountFragment.AuthenticationFragment;
import com.example.ga_23s2.view.accountFragment.RequireLoginFragment;
import com.example.ga_23s2.view.p2pFragment.ChatFragment;
import com.example.ga_23s2.view.searchFragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.HashMap;
import java.util.Map;

public class FragmentHandlerFactory {
  @Deprecated
  public FragmentHandler createLoggedInHandler(FragmentManager manager, BottomNavigationView view) {
    Map<NavigationId, Fragment> fragmentMap = new HashMap<>();
    fragmentMap.put(NavigationId.NAVI_SEARCH, new SearchFragment());
    fragmentMap.put(NavigationId.NAVI_CHAT, new ChatFragment());
    fragmentMap.put(NavigationId.NAVI_ACCOUNT, new AccountFragment());
    return new FragmentHandler(manager, fragmentMap, view);
  }

  public FragmentHandler createGuestHandler(FragmentManager manager, BottomNavigationView view) {
    Map<NavigationId, Fragment> fragmentMap = new HashMap<>();
    fragmentMap.put(NavigationId.NAVI_SEARCH, new SearchFragment());
    Fragment requireLoginFragment = new RequireLoginFragment();
    fragmentMap.put(NavigationId.NAVI_CHAT, requireLoginFragment);
    fragmentMap.put(NavigationId.NAVI_ACCOUNT, new AuthenticationFragment());
    return new FragmentHandler(manager, fragmentMap, view);
  }
}
