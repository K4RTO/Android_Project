package com.example.ga_23s2.cloud.dataAccess;

public class Keys {
  public enum CollectionName {
    PUBLIC_USER("users"),
    GAME("games"),
    UPDATE_HISTORY("update_history"),
    POSTS("posts"),
    MESSAGES("chatMessages");

    public final String KEY;

    CollectionName(String KEY) {
      this.KEY = KEY;
    }
  }

  public enum GameDocumentName {
    GAME_NAME("name"),
    RELEASE_DATE("release_date"),
    PLATFORMS("platforms"),
    RAM("RAM"),
    PRICE("price"),
    REQUIRED_AGE("required_age");
    String KEY;

    GameDocumentName(String KEY) {
      this.KEY = KEY;
    }

    public String KEY() {
      return KEY;
    }
  }

  public enum MessageDocumentName {
    SENDER_ID("senderId"),
    RECEIVER_ID("receiverId"),
    MESSAGE("message"),
    TIME_STAMP("timeStamp");

    String KEY;

    MessageDocumentName(String KEY) {
      this.KEY = KEY;
    }

    public String KEY() {
      return KEY;
    }
  }

  public enum PostDocumentName {
    USER_ID("userId"),
    TITLE("title"),
    GAME_NAME("game"),
    CONTENTS("contents"),
    POST_DATE("date"),
    BOOKMARK("bookmark"),
    POST_IMG("postImg");
    String KEY;

    PostDocumentName(String KEY) {
      this.KEY = KEY;
    }

    public String KEY() {
      return KEY;
    }
  }

  public enum UserDocumentName {
    ACCOUNT("account_name"),
    EMAIL("email"),
    PROFILE_IMG("profile_image"),
    LOCATION("location"),
    DATE_OF_BIRTH("date_of_birth"),
    BLOCK("block"),
    FOLLOW("follow"),
    BOOKMARK("bookmark");
    String KEY;

    UserDocumentName(String KEY) {
      this.KEY = KEY;
    }

    public String KEY() {
      return KEY;
    }
  }
}
