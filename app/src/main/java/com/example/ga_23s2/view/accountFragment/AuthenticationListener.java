package com.example.ga_23s2.view.accountFragment;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.CloudResult;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.FragmentAuthenticationBinding;
import com.example.ga_23s2.model.Tools.InvalidDateRangeException;
import com.example.ga_23s2.model.Tools.InvalidDateStringException;
import com.example.ga_23s2.model.Tools.ParseString;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.viewmodel.Session;
import com.example.ga_23s2.viewmodel.authentication.UserCredential;
import com.example.ga_23s2.viewmodel.state.UserState;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationListener {

  FragmentAuthenticationBinding binding;
  Context context;
  private final int PROGRESS_BAR_SHOW_TIME = 400;
  private final int UI_UPDATE_TIME = 300;

  public AuthenticationListener(FragmentAuthenticationBinding binding, Context context) {
    this.binding = binding;
    this.context = context;
  }

  public void setListener() {
    hideBoxRegister();
    toggleLoginBtnTask(true);
  }

  private void showBoxRegister() {
    // switch to registration page
    showProgressBarShort();
    // delayed runnable
    new Handler(Looper.getMainLooper())
        .postDelayed(
            () -> {
              binding.boxRegister.setVisibility(View.VISIBLE);
              toggleLoginBtnTask(false);
            },
            UI_UPDATE_TIME);

    binding.textToggleLogin.setOnClickListener(view -> hideBoxRegister());
    binding.btnLogin.setOnClickListener(this::btnRegisterListener);
  }

  private void hideBoxRegister() {
    // switch to login page
    showProgressBarShort();
    new Handler(Looper.getMainLooper())
        .postDelayed(
            () -> {
              binding.boxRegister.setVisibility(View.INVISIBLE);
              toggleLoginBtnTask(true);
            },
            UI_UPDATE_TIME);
    binding.textToggleLogin.setOnClickListener(view -> showBoxRegister());
    binding.btnLogin.setOnClickListener(this::btnLoginListener);
  }

  private void btnLoginListener(View v) {
    UserCredential userCredential = new UserCredential();
    userCredential
        .addEmail(binding.edtLoginEmail.getText().toString())
        .addPassword(binding.edtLoginPassword.getText().toString());
    CloudResult<AuthResult> task = Session.getInstance().logIn(userCredential);
    task.addOnCompleteListener(this::loginOnCompleteListener);
  }

  private void btnRegisterListener(View v) {
    // check valid password
    String p1 = binding.edtLoginPassword.getText().toString();
    if (p1.length() == 0) {
      Toast.makeText(context, "Please enter a password", Toast.LENGTH_SHORT).show();
      return;
    }
    String p2 = binding.edtConfirmPassword.getText().toString();
    if (!p1.equals(p2)) {
      Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show();
      binding.edtLoginPassword.setText("");
      binding.edtConfirmPassword.setText("");
      return;
    }
    String email = binding.edtLoginEmail.getText().toString();
    if (email.length() == 0) {
      Toast.makeText(context, "Please enter an email", Toast.LENGTH_SHORT).show();
      return;
    }
    // valid dob
    String dateString = binding.edtDateOfBirth.getText().toString();
    Timestamp timestamp = null;
    try {
      timestamp = ParseString.toTimestamp(dateString);
    } catch (InvalidDateStringException e) {
      Toast.makeText(context, "Invalid date format: " + e.getMessage(), Toast.LENGTH_SHORT).show();
      binding.edtDateOfBirth.setText("");
      return;
    } catch (InvalidDateRangeException e) {
      String msg = "You are either too old or too young: " + e.getMessage();
      Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
      binding.edtDateOfBirth.setText("");
      return;
    }

    Timestamp finalTimestamp = timestamp;
    UserCredential userCredential = new UserCredential().addEmail(email).addPassword(p1);
    CloudResult<AuthResult> taskRegister = Session.getInstance().signUp(userCredential);
    taskRegister.addOnSuccessListener(
        t -> {
          String account_name = binding.edtAccountName.getText().toString();
          uploadUserInfo(userCredential.getEmail(), account_name, finalTimestamp);
        });
  }

  private void uploadUserInfo(String email, String accountName, Timestamp dob) {
    UserId userId = new UserId(FirebaseAuth.getInstance().getUid());
    Map<String, Object> map = new HashMap<>();
    map.put(Keys.UserDocumentName.EMAIL.KEY(), email);
    map.put(Keys.UserDocumentName.ACCOUNT.KEY(), accountName);
    map.put(Keys.UserDocumentName.DATE_OF_BIRTH.KEY(), dob);
    Task<Void> task = Session.getInstance().getUserDocument(userId).set(map);
    task.addOnSuccessListener(
        t -> {
          Session.getInstance().changeState(new UserState(userId));
          Toast.makeText(context, "Hello world, " + accountName, Toast.LENGTH_SHORT).show();
        });
    task.addOnFailureListener(
        t -> {
          String e = "Profile update failure: " + t.getMessage();
          Toast.makeText(context, e, Toast.LENGTH_SHORT).show();
          hideBoxRegister();
        });
  }

  public void loginOnCompleteListener(Task<AuthResult> task) {
    if (task.isSuccessful()) {
      Session session = Session.getInstance();
      UserId userId = new UserId(FirebaseAuth.getInstance().getUid());
      session.changeState(new UserState(userId));
      session.notifyLoggedIn();
      Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
    } else {
      String e = "Login Failure: " + task.getException().getMessage();
      Toast.makeText(context, e, Toast.LENGTH_SHORT).show();
      binding.edtLoginPassword.setText("");
    }
  }

  private void showProgressBarShort() {
    binding.progressBar.setVisibility(View.VISIBLE);
    new CountDownTimer(PROGRESS_BAR_SHOW_TIME, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {}

      @Override
      public void onFinish() {
        binding.progressBar.setVisibility(View.INVISIBLE);
      }
    }.start();
  }

  public void toggleLoginBtnTask(boolean isLogin) {
    RelativeLayout.LayoutParams params =
        (RelativeLayout.LayoutParams) binding.btnLogin.getLayoutParams();
    if (isLogin) {
      params.addRule(RelativeLayout.BELOW, binding.edtLoginPassword.getId());
      binding.btnLogin.setText(R.string.login);
    } else {
      params.addRule(RelativeLayout.BELOW, binding.boxRegister.getId());
      binding.btnLogin.setText(R.string.register);
    }
    binding.btnLogin.setLayoutParams(params);
    binding.edtConfirmPassword.setText("");
  }
}
