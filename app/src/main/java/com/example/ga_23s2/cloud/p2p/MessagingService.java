package com.example.ga_23s2.cloud.p2p;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * @author Yifan Xiao
 */
public class MessagingService extends FirebaseMessagingService {

  @Override
  public void onNewToken(@NonNull String token) {
    super.onNewToken(token);
    Log.d("FCM", "Token: " + token);
  }

  @Override
  public void onMessageReceived(@NonNull RemoteMessage message) {
    super.onMessageReceived(message);
    Log.d("FCM", "Message: " + message.getNotification().getBody());
  }
}
