package com.example.ga_23s2.model.userActivity;

import com.example.ga_23s2.model.post.PostId;
import java.util.*;

/**
 * @author
 */
/**
 * Keep track of bookmarks.
 * <li>number of bookmarks of every post.
 * <li>set of bookmarks current user has. <br>
 *     Prevents counting repeated bookmark by the same user.
 */
public class BookmarkService {
  //  private Map<PostId, Integer> bookmarkCount; // #of bookmarks by post
  private Set<PostId> bookmarked; // record of current user's bookmarks

  //  private FirebaseUser currentUser;
  //  private DataAccess dataAccess;

  public BookmarkService(Set<PostId> bookmarked) {
    //    this.bookmarkCount = new HashMap<>();
    this.bookmarked = bookmarked;
    //    this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    //    this.dataAccess = new DataAccess();

    // Fetch the current user's bookmarks
    //    if (currentUser != null) {
    //      dataAccess.fetchUserBookmarkedPosts(
    //          currentUser.getUid(),
    //          new DataAccess.UserBookmarkCallback() {
    //            @Override
    //            public void onBookmarksLoaded(Set<PostId> bookmarkedPosts) {
    //              bookmarked = bookmarkedPosts;
    //            }
    //
    //            @Override
    //            public void onFailure(String errorMessage) {
    //              // Handle error (e.g. log it or show it to the user)
    //            }
    //          });
    //    }
  }

  public boolean isBookmarked(PostId postId) {
    if (bookmarked.contains(postId)) {
      return true;
    }
    return false;
  }

  /**
   * Get the number of bookmarks of a particular post.
   *
   * @param postId the ID of the post
   * @return the number of bookmarks
   */
  //  public int getBookmarkCountOf(PostId postId) {
  //    return bookmarkCount.getOrDefault(postId, 0);
  //  }

  /**
   * Increment or decrement the bookmark count of a post.
   *
   * @param postId the ID of the post
   * @param increment whether to increment (true) or decrement (false)
   */
  //  private void updateBookmarkCountOf(PostId postId, boolean increment) {
  //    int count = getBookmarkCountOf(postId);
  //    if (increment) {
  //      bookmarkCount.put(postId, count + 1);
  //    } else if (count > 0) {
  //      bookmarkCount.put(postId, count - 1);
  //    }

  // Update the count using DataAccess
  //    dataAccess.updateBookmarkCountOfPost(postId, bookmarkCount.get(postId));
  //  }

  /**
   * Toggle the bookmark status of a post for the current user.
   *
   * <p>// * @param postId the ID of the post
   */
  //  public void toggleBookmark(PostId postId) {
  //    if (currentUser == null) return; // Ensure we have a logged-in user
  //    boolean addBookmark = !bookmarked.contains(postId);
  //
  //    // Toggle in local set
  //    if (addBookmark) {
  //      bookmarked.add(postId);
  //    } else {
  //      bookmarked.remove(postId);
  //    }

  // Update Firestore
  //    dataAccess.updateUserBookmarkedPosts(
  //        postId,
  //        currentUser.getUid(),
  //        addBookmark,
  //        new DataAccess.UpdateBookmarkCallback() {
  //          @Override
  //          public void onSuccess() {
  //            // Successfully updated in Firestore
  //            updateBookmarkCountOf(postId, addBookmark);
  //          }
  //
  //          @Override
  //          public void onFailure(String errorMessage) {
  //            // Handle error, perhaps revert the local toggle if the remote update failed
  //          }
  //        });
  //  }

  public Set<PostId> getBookmarkedPostIds() {
    return bookmarked;
  }
}
