package com.example.ga_23s2.view.fragmentHandler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.ga_23s2.R;
import com.example.ga_23s2.view.accountFragment.AccountFragment;
import com.example.ga_23s2.view.accountFragment.AuthenticationFragment;
import com.example.ga_23s2.view.accountFragment.RequireLoginFragment;
import com.example.ga_23s2.view.p2pFragment.ChatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Map;

/**
 * Manages the change of fragment.<br>
 * </> Motivation: `replace` method in FragmentManager destroys all other fragments. Use handler to
 * keep fragments in background. Able to update views on listener of navigation bar, and state
 * changes.
 *
 * @author Huanjie Zhang
 */
public class FragmentHandler {
  private final FragmentManager manager;
  private Map<NavigationId, Fragment> fragmentMap;
  private NavigationId currentId;
  private NavigationId previousId;
  private final Integer FRAME_ID = R.id.frame_layout;
  private BottomNavigationView navigationView;

  // used for showing a clicked effect when fragment changed by code.

  protected FragmentHandler(
      FragmentManager manager, Map<NavigationId, Fragment> fragmentMap, BottomNavigationView view) {
    this.manager = manager;
    // add Fragments
    this.fragmentMap = fragmentMap;

    for (Fragment fragment : fragmentMap.values()) addFragmentAtBackground(fragment);
    // current tab id
    this.currentId = fragmentMap.keySet().stream().findAny().orElse(NavigationId.NAVI_SEARCH);
    this.previousId = currentId;
    this.navigationView = view;
  }

  /** add fragment to main activity and run it at background without displaying. */
  private void addFragmentAtBackground(Fragment fragment) {
    if (manager.getFragments().contains(fragment)) return;
    manager.beginTransaction().add(FRAME_ID, fragment).hide(fragment).commit();
  }

  /** Upon state changed, display new fragment and update the reference in map. */
  private void replaceFragment(NavigationId navigationId, Fragment newFragment) {
    manager.beginTransaction().detach(getFragmentById(navigationId)).commit();
    fragmentMap.put(navigationId, newFragment);
    addFragmentAtBackground(newFragment);
  }

  private Fragment getFragmentById(NavigationId id) {
    if (!fragmentMap.containsKey(id)) throw new IllegalArgumentException("Invalid id for fragment");
    return fragmentMap.get(id);
  }

  /**
   * Hide previous fragment and show the new one.
   *
   * @param id: new id clicked on navigation bar
   */
  public void swapFragment(NavigationId id) {
    manager.beginTransaction().hide(getFragmentById(currentId)).show(getFragmentById(id)).commit();
    previousId = currentId;
    currentId = id;
  }

  private void swapToPreviousFragment() {
    swapFragment(previousId);
    NavigationId tmp = previousId;
    previousId = currentId;
    currentId = tmp;
  }

  /** When notified by session, change the views. */
  public void switchToLoggedIn() {
    replaceFragment(NavigationId.NAVI_ACCOUNT, new AccountFragment());
    replaceFragment(NavigationId.NAVI_CHAT, new ChatFragment());
    swapToPreviousFragment();
    navigationView.setSelectedItemId(previousId.id);
  }

  /** When notified by session, change the views. */
  public void switchToLoggedOut() {
    replaceFragment(NavigationId.NAVI_ACCOUNT, new AuthenticationFragment());
    replaceFragment(NavigationId.NAVI_CHAT, new RequireLoginFragment());
    swapToPreviousFragment();
    navigationView.setSelectedItemId(previousId.id);
  }
}
