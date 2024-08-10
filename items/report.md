# [G20 - GoodGame] Report

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#code-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-management)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)


## Administrative

- Firebase Repository Link: https://console.firebase.google.com/project/g20-ga-23s2/overview
    - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
    - Username: comp2100@anu.edu.au	Password: comp2100
    - Username: comp6442@anu.edu.au	Password: comp6442

- <u>Squash commits are encouraged by default</u>, because members create small branches and merge to 'main'. 
## Team Members and Roles
The key area(s) of responsibilities for each member

| UID      |     Name      |                                        Role/Area |
|:---------|:-------------:|-------------------------------------------------:|
| u6469985 | Zhaoyan Cong  |            Game dataset, Bookmark, Filter & Sort |
| u7630965 |  Yifan Xiao   |       P2P messaging, GPS, UI, Firestore database |
| u7618352 |    Han Yan    |               SearchFragment, Tokenizer, Testing |
| u7633338 | Huanjie Zhang | Design pattern, Model algorithms, UI improvement |


## Summary of Individual Contributions


---
1. **U6469985, Zhaoyan Cong** I have 25% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Feature [DataFiles]
        - 2,500 game dataset: [games.txt](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/assets/games.txt)
    - Feature [Data-Bookmark]
        - class BookmarkService: [BookmarkService.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/userActivity/BookmarkService.java)
        - class BookmarkAdapter: [BookmarkAdapter.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/BookmarkAdapter.java)
        - class BookmarkActivity: [BookmarkActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/BookmarkActivity.java)
    - Feature [Search]
        - class FilterService: [FilterService.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/FilterService.java)
        - class SortService: [SortService.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/SortService.java)
    - UI [Layout]
        - bookmark: [activity_bookmark.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_bookmark.xml)
          <br><br>
---  
2. **U7630965, Yifan Xiao**  I have 25% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Feature [Data-GPS]
        - interface GpsListener: [GpsListener.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/GpsListener.java)
        - class LocationHelper: [LocationHelper.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/LocationHelper.java)
    - Feature [Data-Profile]
        - class ProcessImage: [ProcessImage.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/ProcessImage.java)
        - class UpdateProfileActivity: [UpdateProfileActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/UpdateProfileActivity.java)
        - class UserProfileActivity: [UserProfileActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/userProfile/UserProfileActivity.java)
    - Feature [P2P-DM]
        - class ChatFragment: [ChatFragment.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatFragment.java)
        - class ChatActivity: [ChatActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatActivity.java)
        - class ChatAdapter: [ChatAdapter.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatAdapter.java)
        - class ChatMessage: [ChatMessage.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/p2p/ChatMessage.java)
        - class ChatMsgHandler: [ChatMsgHandler.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/p2p/ChatMsgHandler.java)
    - Feature [P2P-Block]
        - class BlockUserService: [BlockUserService.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/userActivity/BlockUserService.java)
        - class BlockedUsersActivity: [BlockedUsersActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/BlockedUsersActivity.java)
    - Feature [FB-Persist]
        - class DataAccess: [DataAccess.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/dataAccess/DataAccess.java)
        - interface UserCallback: [UserCallback.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/userProfile/UserCallback.java)
        - interface LoadPostsCallBack: [LoadPostsCallBack.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/LoadPostsCallBack.java)
        - class PostManager: [PostManager.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/addPost/PostManager.java)
    - Other features:
        - class AddPostActivity: [AddPostActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/addPost/AddPostActivity.java)
        - class PostAdapter: [PostAdapter.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/PostAdapter.java)
        - class PostDetailActivity: [PostDetailActivity.java](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/PostDetailActivity.java)
    - UI Layout:
        - [activity_add_post.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_add_post.xml)
        - [activity_blocked_users.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_blocked_users.xml)
        - [activity_chat.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_chat.xml)
        - [activity_post_details.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_post_details.xml)
        - [activity_update_profile.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_updata_profile.xml)
        - [activity_user_profile.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/activity_user_profile.xml)
        - [fragment_account](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/fragment_account.xml)
        - [fragment_chat.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/fragment_chat.xml)
        - [post_cardview.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/post_cardview.xml)
        - [received_message_container.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/received_message_container.xml)
        - [sent_message_container.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/sent_message_container.xml)
        - [toolbar_header.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/toolbar_header.xml)
        - [user_list_cardview.xml](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/res/layout/user_list_cardview.xml)
          <br><br>

- **Code and App Design**
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]*
        - Toolbar header: implement a common header that allows users to navigate back to the previous page and access other users' profiles.
        - CardView: use CardView layout to display users and posts in a unique and stylish manner.
        - Expandable ListView: ListView in a ScrollView cannot display the full list, implement a ExpandableHeightListView instead.
          <br><br>

---
3.  **U7618352, Han Yan**  I have 25% contribution, as follows: <br>
- **Code Contribution in the final App**
  - Feature [Parser and Tokenizer] 
    - class Parser: [Parser.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/edit/main/-/app/src/main/java/com/example/ga_23s2/model/postService/Parser.java)
    - class Tokeniser: [Tokeniser.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/main/java/com/example/ga_23s2/model/postService/Tokeniser.java/)
  - Feature [SearchFragment]
    - class SearchFragment: [SearchFragment.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchFragment.java/)
  - Feature [Testing]
    - class ParseStringTest [ParseStringTest.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/test/java/com/example/ga_23s2/model/Tools/ParseStringTest.java/)
    - class ParserTest [ParserTest.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/test/java/com/example/ga_23s2/model/postService/ParserTest.java/)
    - class TokeniserTest [TokeniserTest.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/test/java/com/example/ga_23s2/model/postService/TokeniserTest.java/)
    - class RBTreeTest [RBTreeTest.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/test/java/com/example/ga_23s2/model/tree/RBTreeTest.java/)
  - others [Fragmented functions]
    - KeyBoard Hiding:
      - Search bar [SearchFragment.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchFragment.java/)
      - Create Post [AddPostActivity.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/main/java/com/example/ga_23s2/view/posts/addPost/AddPostActivity.java/)
      - Navigate bar [MainActivity.java](https://gitlab.cecs.anu.edu.au/-/ide/project/u7633338/ga-23s2/tree/main/-/app/src/main/java/com/example/ga_23s2/view/MainActivity.java/)

- **Others**: (only if significant and significantly different from an "average contribution")
  - Slides Presentation: Prepare some images and framework of slides
  - Video creation: Edit Videos and Add Subtitles

---
4. **U7633338, Huanjie Zhang**  I have 25% contribution, as follows: <br>
- **Code Contribution in the final App**
  - Feature 
      - Feature [LoadShowData].
        *  [class GameManager](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/addPost/GameManager.java)
        * [class DataStorageService](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataStorageService.java)
        * [class DataAccessService](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataAccessService.java)
      - Feature [Search]
        * [SearchFragment](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchFragment.java)
        * [SearchHelper](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchHelper.java)
        * [Tokeniser](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokeniser.java)
        * [Parser](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Parser.java)
        * [PostServiceEntry](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/PostServiceEntry.java)

      - Feature [FB-Auth]
        * [Class EmailStrategy, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/EmailStrategy.java)
        * [Interface _AuthStrategy_, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/AuthStrategy.java)
        * [Method Session.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L48-50)
        * [Method GuestState.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/GuestState.java#L22-24)
        * [Method uploadUserInfo](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/AuthenticationListener.java#L129-147)
        * [Method Session.signUp](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L60-62)
        * [Method GuestState.signUp](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/GuestState.java#L33-35)
      - Feature [FB-Persist]
        * [class Keys, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/dataAccess/Keys.java)
        * [class DataAccess, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/dataAccess/DataAccess.java)
        * [class Session, methods getUserCollection() getChatCollection(), getPostsCollection()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L84-103)
          
- Design Pattern 
  - _a.Singleton_
       [Class Session, methods getInstance](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L25-35);
       and in
       [Class GameStorageService, methods getInstance](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataStorageService.java);

  - _b. Session-State Pattern_ 
    [Class Session](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java);
  - _c. Iterator Pattern_
   [Class TreeInOrderIterator](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeInOrderIterator.java)
 and
 [Class TreeReverseOrderIterator](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeReverseOrderIterator.java),
  - _d. Strategy Pattern_
    [Interface AuthStrategy](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authentication/authenticationStrategy/AuthStrategy.java) and [EmailStrategy](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authentication/authenticationStrategy/EmailStrategy.java).
  - _e.Adapter Pattern_ [Class NavigationIdAdapter](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/NavigationIdAdapter.java); 
   [Method `getFragmentById(NavigationId id)`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/FragmentHandler.java#L57-60) 
   and [enum `NavigationId`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/NavigationId.java).


- **Code and App Design**
  - Registration: Combined login and registration, show `progressBar` when changed.
  - NavigationBar: created navigation bar.
  - Account Fragment: created boxed `TextView` working as 'button'.

- **Others**: 
    - Gradle setup, consistent google-style formatting using spotless.




## Application Description



>Our Application aims to cater to the growing need of video game market

*GoodGame* is a social media application specifically targeting gamers on Steam,
it provides a platform for gamers to conduct interactions and search game information online,
such as p2p online chats and bookmark functions for game posts

### Application Use Cases and or Examples

*Target Users: Gamers on Steam*
*Scenario:* Gamer Looking to Interact and Discuss about Games
1. *Tommy logs into GoodGame and navigates to the search page*
2. *He makes a post sharing his initial impressions and thoughts, tagging it appropriately*
3. *Sarah, another gamer, sees Tommy’s post and likes it, and wants to chat with Tommy after bookmarking the post*
4. *Tommy and Sarah engage in a discussion, exchanging ideas and strategies for the game*
5. *But Sarah finds Tommy's language not appropriate, offensive in some cases, and thus sends him into her blocklist*

<hr> 

### Application UML

![UML Diagram](media/_examples/COMP6442.png) <br>

<hr>

## Code Design and Decisions

<hr>

### Data Structures


*I used the following data structures in my project:*
1. *RB Tree*
    * *Objective: used for storing 2500 instances of `GameEntry`.*
    * *Code Locations: defined in 
   [Class RBTree<K extends Comparable<K>, V>](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/RBTree.java),
   [Class RBTreeNode<K extends Comparable<K>, V>](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/RBTreeNode.java),
   [Class TreeInOrderIterator<K extends Comparable<K>, V> implements Iterator<RBTreeNode<K, V>>](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeInOrderIterator.java),
   and [Class TreeReverseOrderIterator<K extends Comparable<K>, V> implements Iterator<RBTreeNode<K, V>>](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeReverseOrderIterator.java), 
   processed using [Class DataAccessService, lines l28-l55](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataAccessService.java#L28-L55);
    * *Reasons:*
        * *Red-black tree is faster in insertion because of less adjustment and rotation.
            User may want faster opening of application,  
            since data is loaded from internal storage on every "onCreate" lifecycle.*
        * *Height difference is small in the worst case between Red-black tree and AVL tree,
           and hence does not affect asymptotic runtime in finding the element.*


<hr>

### Design Patterns
1. **_Singleton Pattern_**
    * *Objective: Used for storing `Session` and `GameStorageService`.* Application may share access to state, data and method.
    * *Code Locations: defined in [Class Session, methods getInstance](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L25-35);*
     *and in [Class GameStorageService, methods getInstance](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataStorageService.java);*
    * *Reasons:*
        * `Session` is unique throughout user's activity.
        * `Session` connects view and model, with stationary manner.
        * Keep unique copy of data in `GameStorageService`.
      <br></br>
2. **_Session-State Pattern_**
    * *Objective: used for storing current state of different user.*
    * *Code Locations: defined in [Class State](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/State.java)
                        and [Class Session](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java);
    * *Reasons:*
        * View access model methods through `Session`, and may expect different behaviours based on state
        * `Session` may notify view to update on login and sign-out.
      <br></br>
3. **_Iterator Pattern_**
    * *Objective: May iterate through data differently.*
    * *Code Locations: defined in [Class TreeInOrderIterator](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeInOrderIterator.java),
      and [Class TreeReverseOrderIterator](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/tree/TreeReverseOrderIterator.java),
    * *Reasons:*
        * Data can be stored in tree and retrieved with ascending or descending order;
        * Hide the complexity of tree from users; 
        * Prevent potential changes to data if exposing the values. 
<br></br>
4. **_Strategy Pattern_**
    * *Objective: Adaptive for various login methods,* which might be implemented in the future.
    * *Code Locations: defined in [Interface AuthStrategy](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authentication/authenticationStrategy/AuthStrategy.java) 
        and [EmailStrategy](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authentication/authenticationStrategy/EmailStrategy.java)
    * *Reasons:*
        * Login button may perform different actions with the same interface;
        * Easy to extend without changing the method call and interface;
      <br></br>
5. **_Adapter Pattern_**
    * *Objective: Making two classes compatible.*
    * *Code Locations:* <br> defined in [Class `NavigationIdAdapter`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/NavigationIdAdapter.java); 
   <br>processed using [Method `getFragmentById(NavigationId id)`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/FragmentHandler.java#L57-60) 
   and [enum `NavigationId`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/fragmentHandler/NavigationId.java).
    * *Reasons:*
        -  `FragmentHandler` manages switching of fragment by using `NavigationId`;
        <br> While `navigationView`'s listener has input of  `Integer`;
        <br> Adapter connects the two together.
<hr>

### Parser

### <u>Grammar(s)</u>

Production Rules: <br> Brief summary as discussed in git.
(https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/issues/3)



### <u>Tokenizers and Parsers</u>


#### Usage
Tokenisers and parsers are used in transforming search query into filters and comparators.

#### Structure
- Delimiter: semi-colon, "; ". 
- White space is ignored.
- Standard query: `<FIELD> <OPERATOR> <VALUE>;` 

#### Tokens
As in the interface [`Token`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/tree/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens)
- ##### [Operators:](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens/OperatorToken.java)
  - Has two subclass [`SortToken`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens/SortToken.java) and [`FilterToken`](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens/FilterToken.java)
  - Sorting operators: `ASC` or ascending, `DESC` or descending.
  - Filter operators: `=`, `<`, `>`, `contains`.

- ##### [FIELD:](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens/FieldToken.java)
    -  Related to fields of `Post` and its `GameEntry`

- ##### [VALUE:](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokens/ValueToken.java)
    - Store as text and should be parsed to other type if necessary.


##### Logics
- When `OPERATOR` is sorting, `Parser` tries to find a `FIELD` which can be compared.
`Parser` will then inform the other classes how to sort and how to resolve same values.
<br></br>
  
- When `OPERATOR` is filtering, it can either take the range or exactly values of  `FILED`. If `FILED` is not specified, parser takes default `ANY_FIELD`.



#### Advantages
- Resolve some typos for the tokens;
- Flexible and extendable in constructing the query; 
- Infer ranking based on order of filtering.

#### Others: `and` & `or` operator
- Users might want more flexibility in searching rather than meeting all criteria in query.
Operators like `&` and `|` can be used to link multiple predicates. (This is not implemented.)
<br>
<hr>

## Implemented Features


### Basic Features
1. [LogIn]. Users must be able to log in (not necessarily sign up). (easy)
   * Code: 
     - [Class EmailStrategy, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/EmailStrategy.java)
     - [_Interface_ AuthStrategy, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/AuthStrategy.java)
     - [Method Session.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L48-50)
     - [Method GuestState.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/GuestState.java#L22-24)
   * Description of your implementation:
     - View: when login button is clicked, email and password are wrapped in `UserCredential`.
     - Viewmodel: `Session` take `UserCredential`, `State` determines the behaviour.
     - Model: at `GuestState`, AuthStrategy tries the login and pass result back to view.
     <br></br>

2. [DataFiles]. Create data file(s) with at least 2,500 valid data instances, which are then used to feed your app to simulate different users’ interactions on with the App. A data item can be an action (e.g., a new product added to the server, a user requested to view a profile; etc.). (easy)
   * Data File: [games.txt](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/assets/games.txt)
   * The data comprises a collection of 2500 video games, including information about their name, platforms, price, release dates, RAM, and required age. 
   * Users have the flexibility to select/search any of these games and add posts to share their experiences about the game.
     <br></br>
   
3. [LoadShowData]. When a user is logged in, load data (from the file(s) and/or Firebase) at regular time intervals, and visualise the same in the App. (e.g., If the main page contains a list of featured products, the user may see an increased number of products; as well as receive notifications from interactions simulated from the data stream). (medium)
   * Data File [games.txt](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/assets/games.txt)
   * Link to the Firebase repo: [Firebase](https://console.firebase.google.com/project/g20-ga-23s2/overview)
   * Code:
      - [class GameManager](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/addPost/GameManager.java)
      - [class DataStorageService](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataStorageService.java)
      - [class DataAccessService](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/DataService/DataAccessService.java)
        <br></br>
     
4. [Search]. Users must be able to search for information on your app. (medium)
   * Code: 
     * [SearchFragment](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchFragment.java)
     * [SearchHelper](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/searchFragment/SearchHelper.java)
     * [Tokeniser](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Tokeniser.java)
     * [Parser](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/Parser.java)
     * [PostServiceEntry](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/postService/PostServiceEntry.java)
   * Implementation: 
     * Search query is passed segment by segment into tokeniser;
     * Tokeniser mapped the text to one of `FieldToken`, `OperatorToken` or `ValueToken`;
     * Tokeniser takes care of some synonyms and typos. Typos are fixed by calculating [DL Distance](https://en.wikipedia.org/wiki/Damerau–Levenshtein_distance).
       (The definition in Wiki is used in implementation but not algorithm. Algorithms in this repo and on wiki happen to be very similar.)
     * Parser takes tokens and passes PostServiceEntry into SearchHelper.

### Custom Features
Feature Category: Greater Data Usage, Handling and Sophistication <br>
1. [Data-GPS]. Use GPS information based on location data in your App. (easy)
   * Code:
      - [class AccountFragment, method getLocation](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/AccountFragment.java#L111-115)
      - [interface GpsListener, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/GpsListener.java)
      - [class LocationHelp, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/LocationHelper.java)
   * Description of your implementation: This feature is designed to requests location permission, fetches the user's location, and updates the location information for the current user in a database. It uses the LocationManager, Geocoder, and Firebase Firestore to achieve this functionality.
      - `LocationHelper Class`: It implements the LocationListener and GpsListener interfaces to handle location changes and GPS status changes.
      - `checkLocationPermissionAndFetch()` method checks whether the app has permission to access the device's location. If not, it calls the `askPermission()` method to request permission; otherwise, it directly calls the `showLocation()` method to retrieve the location.
      - `onLocationChanged()` method is called when the device's location changes. It checks if the location is not null and if the user is not in a guest state. If conditions are met, it uses the Geocoder to get the administrative area from the latitude and longitude of the location. It then updates the UI element txtLocation and sends the location data to a database.

2. [Data-Profile] Create a Profile Page for Users or any Entities, which contains a media file (image, animation (e.g. gif), video). (easy)
   * Code:
      - [class ProcessImage, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/ProcessImage.java)
      - [class UserLoader, method loadUserDetails](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/UserLoader.java#L31-45)
      - [class UpdateProfileActivity, methods loadUserDetails and setImage](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/UpdateProfileActivity.java#L58-93)
   * Description of your implementation: A user profile page that users to view and update their profile image by selecting an image from their device's gallery, encoding it into a format suitable for storage, and updating it in a Firestore database.
      - `ProcessImage Class`: include `encodeImage()` and `decodeImage()` method that converts the image between between Bitmap and String.
      - `UpdateProfileActivity Class`: `setImage()` method allow users to set a new profile image on their device's gallery and updates the profile image in the Firestore database.
      - `UserLoader Class`: Loads the user's profile image from Firestore. If the user has a profile image stored, it decodes and displays it in an ImageView. If not, it sets a default image.
        <br><br>

Feature Category: Firebase Integration <br>

3. [FB-Auth].  Use Firebase to implement User Authentication/Authorisation. (easy)
   * Code:
        - [Class EmailStrategy, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/EmailStrategy.java)
        - [Interface _AuthStrategy_, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/authenticationStrategy/AuthStrategy.java)
        - [Method Session.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L48-50)
        - [Method GuestState.login](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/GuestState.java#L22-24)
        - [Method uploadUserInfo](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/accountFragment/AuthenticationListener.java#L129-147)
        - [Method Session.signUp](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L60-62)
        - [Method GuestState.signUp](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/state/GuestState.java#L33-35)
   * Description of your implementation:
       - login and sign-up are managed by Firebase, using FirebaseAuth.
       - `Session` and `State` manage the login methods (future implementation) and whether a user is logged in.
       - At registration, the application requires confirmation of password matches, and the date of birth is well formatted.
       - Upon successful registration, application uploads account name and date of birth to Firebase, and swap to logged-in state.
         <br></br>

4. [FB-Persist] Use Firebase to persist all data used in your app. (medium)
   * Code:
        - [class Keys, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/dataAccess/Keys.java)
        - [class DataAccess, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/dataAccess/DataAccess.java)
        - [class Session, methods getUserCollection(), getChatCollection(), getPostsCollection()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/viewmodel/Session.java#L84-103)
        - [interface UserCallback](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/userProfile/UserCallback.java)
        - [interface LoadPostsCallBack](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/LoadPostsCallBack.java)
        - [class PostManager](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/posts/addPost/PostManager.java)
   * Description of your implementation:
        - Initialise an instance of Firestore in activities requiring data retrieval from the Firestore database. When user update any information, use `set()` or `update()` method to update the data within Firestore. To obtain data from Firestore, employ the `get()` method and either the `onCompleteListener()` or `onSuccessListener()` to acquire a DocumentSnapshot for data loading.
        - Implement enums for collection and field names when adding or retrieving data from Firestore. to prevent typo.
        - Use `callbackflow` to transmit data retrieved from Firestore for intermediate process of data.
     <br><br>

5. [FB-Persist-Extension (P2P message and users, not implemented in posts)] Without restarting, the app should be updated synchronously as the remote database (Firebase) is updated. This means that users will be able to see the instant updates from another user/content provider. (hard)
    * Code:
        - [class ChatMsgHandler, method createEventListener() and handleDocumentChanges()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/p2p/ChatMsgHandler.java#L35-59)
    * Description of your implementation: Use Firestore's EventListener to handling real-time updates of chat messages and users.
        - `createEventListener()` method sets up the listener to respond to changes in the Firestore collection, if the documentChanges is not null, it calls `handleDocumentChanges()` to process those changes.
        - `handleDocumentChanges()` method processes the specific changes, adding new changes to a list, and updating the RecyclerView adapter accordingly.
      <br><br>


Feature Category: Peer to Peer Messaging <br>

6. [P2P-DM]. Provide users with the ability to message each other directly in private. (hard)
   * Code:
      - [class ChatActivity, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatActivity.java)
      - [class ChatAdapter, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatAdapter.java)
      - [class ChatMsgHandler, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/cloud/p2p/ChatMsgHandler.java)
      - [ChatMessage, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/p2p/ChatMessage.java)
   * Description of your implementation: This P2P chat feature allow users to send direct message to other users. Messages are displayed in a RecyclerView using the `ChatAdapter`, and the `ChatMsgHandler` helps manage the chat messages by listening for changes in Firestore and handling document changes.
      - `ChatActivity class`: Load user id for both sender and receiver. `listenMessages()` method sets up a listener to receive new chat messages and updates the UI when new messages arrive. When users send message, senderId, receiverId, messages, timestamp will be uploaded to Firestore daabase.
      - `ChatAdapter class`: An adapter for populating chat messages in a RecyclerView. Determines the view type for a given chat message based on whether it was sent or received. And binds chat message data to the appropriate view holder, either for sent or received messages.
      - `ChatMsgHandler class`: Use an event listener for Firestore that processes real-time document changes. It sorts and updates the chat message list and notifies the adapter when new messages arrive.
        <br><br>

7. [P2P-Block] Provide users with the ability to ‘block’ and prevent another user from direct messaging them. (medium)
   * Code:
      - [class BlockUserService, entire file](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/model/userActivity/BlockUserService.java)
      - [class UserProfileActivity, blockOrUnblock()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/userProfile/UserProfileActivity.java#L109-117) and [addUsers(), removeUsers()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/userProfile/UserProfileActivity.java#L129-149)
      - [class ChatActivity, method sendMsg()](https://gitlab.cecs.anu.edu.au/u7633338/ga-23s2/-/blob/main/app/src/main/java/com/example/ga_23s2/view/p2pFragment/ChatActivity.java#L94-106)
   * Description of your implementation: Users can view the other users profile and choose to block or unblock the other users. And check if the sender is blocked by the receiver before sending a message. If the sender is blocked, a "blocked" toast message is displayed.
      - `UserProfileActivity class`: It displays the other users profile with button that implemented `blockOrUnblock()` method to check whether that user is blocked by current user and let user choose to block or unblock the other users.
      - `BlockUserService class`: A utility class used to determine if a user is blocked by the current user. It contains a blockedUser set, and the `isBlocked()` method checks if the provided UserId is in the set, indicating that the user is blocked.
      - `sendMsg()` method in `ChatActivity class` load the block information from Firestore database and check if current user is blocked by the other user. If current user is blocked by the other user, he/she cannot send direct message to that user.
        <br><br>

<hr>

### Surprised Features

- Surprised feature is not implemented

<br> <hr>

## Summary of Known Errors and Bugs

1. *Bug 1: Tokeniser*
    - Tokeniser stores text value in lower case, 
      but parser filters original texts without lowering the characters.
    - Date and integer String is not parsed in parser.
   
2. *Bug 2: Search*
    - Search result not properly updated after parsing the query.
   <br>(The result from `parser` is correct but not catch by adapter.)
    - Listener for submitting the query not added (submitting with enter key).

3. *Bug 3: Bookmark*
    - List of bookmarks not presented in ui.

4. *Bug 4: Exceeding quote limit*
    - `EventListener` might take too much reads and crash firebase due to quota limit.
<br> 
<hr>


## Testing Summary



<br> <hr>


## Team Management

### Meetings Records

- *[Team Meeting 1](meeting1.md)*
- *[Team Meeting 2](meeting2.md)*
- *Team Meeting 3* (Online meeting on Oct 3, 2023. Discussed usage of Git)
- *Team Meeting 4* (Online meething on Oct 11, 2023. Members collaborated on class diagram.)


<hr>

### Conflict Resolution Protocol
[Conflict Resolution Protocol](conflict-resolution-protocol.md)
