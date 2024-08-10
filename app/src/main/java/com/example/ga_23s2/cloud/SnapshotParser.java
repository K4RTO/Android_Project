package com.example.ga_23s2.cloud;

import com.example.ga_23s2.R;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.model.User;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.model.p2p.ChatMessage;
import com.example.ga_23s2.model.post.GameEntry;
import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.model.post.PostId;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Yifan Xiao
 * @author Huanjie Zhang
 */
public class SnapshotParser {
  private static String parseChatTime(Date date) {
    return new SimpleDateFormat("MM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
  }

  public static ChatMessage parseChat(DocumentSnapshot document) {
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setSenderId(document.getString(Keys.MessageDocumentName.SENDER_ID.KEY()));
    chatMessage.setReceiverId(document.getString(Keys.MessageDocumentName.RECEIVER_ID.KEY()));
    chatMessage.setMessage(document.getString(Keys.MessageDocumentName.MESSAGE.KEY()));
    chatMessage.setDateObject(document.getDate(Keys.MessageDocumentName.TIME_STAMP.KEY()));
    chatMessage.setDateTime(parseChatTime(chatMessage.getDateObject()));
    return chatMessage;
  }

  public static User parseUser(DocumentSnapshot snapshot) {
    User user = new User();
    String profileImg;
    if ((profileImg = snapshot.getString(Keys.UserDocumentName.PROFILE_IMG.KEY())) == null) {
      profileImg = Session.getInstance().getStringResource(R.string.DEFAULT_IMG);
    }
    String key = Session.getInstance().getStringResource(R.string.fcmToken);
    return user.addUserId(snapshot.getId())
        .addEmail(snapshot.getString(Keys.UserDocumentName.EMAIL.KEY()))
        .addName(snapshot.getString(Keys.UserDocumentName.ACCOUNT.KEY()))
        .addProfileImg(profileImg)
        .addToken(snapshot.getString(key));
  }

  public static Post parsePost(QueryDocumentSnapshot q) {
    UserId userId = new UserId(q.getString(Keys.PostDocumentName.USER_ID.KEY()));
    Date date = q.getDate(Keys.PostDocumentName.POST_DATE.KEY());
    GameEntry gameEntry = new GameEntry();
    gameEntry.addName(q.getString(Keys.PostDocumentName.GAME_NAME.KEY()));

    Integer bookmark;
    if (q.get(Keys.PostDocumentName.BOOKMARK.KEY()) == null) {
      bookmark = 0;
    } else {
      Object bookmarkValue = q.get(Keys.PostDocumentName.BOOKMARK.KEY());

      if (bookmarkValue instanceof Number) {
        bookmark = ((Number) bookmarkValue).intValue();
      } else if (bookmarkValue instanceof String) {
        try {
          bookmark = Integer.parseInt((String) bookmarkValue);
        } catch (NumberFormatException e) {
          bookmark = 0;
        }
      } else {
        bookmark = 0;
      }
    }

    Post post = new Post(userId, date);
    post.setPostId(new PostId(q.getId()));
    post.setTitle(q.getString(Keys.PostDocumentName.TITLE.KEY()));
    post.setContents(q.getString(Keys.PostDocumentName.CONTENTS.KEY()));
    if (q.getString(Keys.PostDocumentName.POST_IMG.KEY()) == null) {
      post.setPostImg(Session.getInstance().getStringResource(R.string.DEFAULT_IMG));
    } else {
      post.setPostImg(q.getString(Keys.PostDocumentName.POST_IMG.KEY()));
    }
    post.setGame(gameEntry);
    post.setBookmarked(bookmark);
    return post;
  }
}
