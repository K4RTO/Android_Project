package com.example.ga_23s2.model.userActivity;

/**
 * @author Huanjie Zhang
 */
/** Pass result of activities and string back to 'view' to make toast. */
public class ToastService {
  Boolean successful;
  String msg;
  String activity;

  public ToastService(Boolean successful) {
    this.successful = successful;
    this.msg = "";
    this.activity = "";
  }

  ToastService addMsg(String msg) {
    return null;
  }

  ToastService addActivity(String activity) {
    return null;
  }
}
