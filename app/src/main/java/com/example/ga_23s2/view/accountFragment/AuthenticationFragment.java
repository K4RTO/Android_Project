package com.example.ga_23s2.view.accountFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.ga_23s2.databinding.FragmentAuthenticationBinding;

/**
 * @author Yifan Xiao
 * @author Huanjie Zhang
 */
public class AuthenticationFragment extends Fragment {

  FragmentAuthenticationBinding binding;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentAuthenticationBinding.inflate(getLayoutInflater());
    AuthenticationListener listener = new AuthenticationListener(binding, getContext());
    listener.setListener();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
