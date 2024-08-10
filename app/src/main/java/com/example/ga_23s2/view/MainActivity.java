package com.example.ga_23s2.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ga_23s2.R;
import com.example.ga_23s2.databinding.ActivityMainBinding;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.view.DataService.DataAccessService;
import com.example.ga_23s2.view.fragmentHandler.FragmentHandler;
import com.example.ga_23s2.view.fragmentHandler.FragmentHandlerFactory;
import com.example.ga_23s2.view.fragmentHandler.NavigationId;
import com.example.ga_23s2.view.fragmentHandler.NavigationIdAdapter;
import com.example.ga_23s2.viewmodel.Session;
import com.example.ga_23s2.viewmodel.state.UserState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Yifan Xiao
 * @author Huanjie Zhang
 */
public class MainActivity extends AppCompatActivity {
  ActivityMainBinding binding;
  Session session;
  FragmentHandler fragmentHandler;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // inflate tab pages
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.navigationView.setOnItemSelectedListener(this::navigationListener);
    new DataAccessService(getApplicationContext());
    init();
  }

  private void init() {
    session = Session.getInstance();
    session.setResources(getResources());

    FragmentHandlerFactory f = new FragmentHandlerFactory();
    fragmentHandler = f.createGuestHandler(getSupportFragmentManager(), binding.navigationView);
    session.setFragmentHandler(fragmentHandler);

    FirebaseUser tmpUser = FirebaseAuth.getInstance().getCurrentUser();
    if (tmpUser != null) {
      session.changeState(new UserState(new UserId(tmpUser.getUid())));
      Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
    }

    fragmentHandler.swapFragment(NavigationId.NAVI_SEARCH);
    binding.navigationView.setSelectedItemId(R.id.navi_search);
  }

  boolean navigationListener(MenuItem item) {
    hideKeyboard();
    fragmentHandler.swapFragment(NavigationIdAdapter.getNavigationId(item.getItemId()));
    return true;
  }

  private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    if (imm != null && getCurrentFocus() != null) {
      imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
  }
}
