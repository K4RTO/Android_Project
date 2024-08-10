package com.example.ga_23s2.model.post;

import com.example.ga_23s2.model.UserId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Huanjie Zhang
 */
public class Post implements Serializable {
  private PostId postId;
  private String title;
  private String contents;
  private final UserId userId;
  private String author; // account name.
  private final Date dateOfCreation; // date format yyyy-mm-dd
  private GameEntry game;
  private Date modifiedDate;
  private Integer bookmarked = 0;
  private String postImg;

  public Post(UserId userId, Date dateOfCreation) {
    this.userId = userId;
    this.dateOfCreation = dateOfCreation;
    this.modifiedDate = dateOfCreation;
  }

  public Post(
      PostId postId,
      String title,
      String contents,
      UserId userId,
      String author,
      Timestamp dateOfCreation,
      GameEntry game,
      Timestamp modifiedDate,
      Integer bookmarked) {
    this.postId = postId;
    this.title = title;
    this.contents = contents;
    this.userId = userId;
    this.author = author;
    this.dateOfCreation = dateOfCreation;
    this.game = game;
    this.modifiedDate = modifiedDate;
    this.bookmarked = bookmarked;
  }

  public void setPostId(PostId postId) {
    this.postId = postId;
  }

  public String getPostImg() {
    return postImg;
  }

  public void setPostImg(String postImg) {
    this.postImg = postImg;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setGame(GameEntry game) {
    this.game = game;
  }

  public void setModifiedDate(Timestamp modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public void setBookmarked(Integer bookmarked) {
    this.bookmarked = bookmarked;
  }

  public PostId getPostId() {
    return postId;
  }

  public String getTitle() {
    if (title == null) return "";
    return title;
  }

  public String getContents() {
    if (contents == null) return "";
    return contents;
  }

  public UserId getUserId() {
    return userId;
  }

  public String getAuthor() {
    if (author == null) return "";
    return author;
  }

  public Date getDateOfCreation() {
    return dateOfCreation;
  }

  public GameEntry getGame() {
    return game;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public Integer getBookmarked() {
    return bookmarked;
  }
}
