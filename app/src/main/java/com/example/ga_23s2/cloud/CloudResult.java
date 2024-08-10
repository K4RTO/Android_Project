package com.example.ga_23s2.cloud;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class CloudResult<T> {
  Task<T> task;
  String activityName;
  String errorMessage;

  public CloudResult(Task<T> task, String activityName) {
    this.task = task;
    this.activityName = activityName;
    this.errorMessage = this.activityName + " failed.";
    task.addOnFailureListener(
        e -> errorMessage = this.activityName + "failed because: " + e.getMessage());
  }

  public boolean isSuccessful() {
    return task.isSuccessful();
  }

  public void addOnCompleteListener(OnCompleteListener<T> listener) {
    task.addOnCompleteListener(listener);
  }

  public void addOnSuccessListener(OnSuccessListener<T> listener) {
    task.addOnSuccessListener(listener);
  }

  public void addOnFailureListener(OnFailureListener listener) {
    task.addOnFailureListener(listener);
  }
}
