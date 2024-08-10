package com.example.ga_23s2.view.searchFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.ga_23s2.databinding.FragmentSearchBinding;
import com.example.ga_23s2.view.DataService.DataStorageService;
import com.example.ga_23s2.view.posts.PostAdapter;
import com.example.ga_23s2.view.posts.PostLoader;
import com.example.ga_23s2.view.posts.addPost.AddPostActivity;
import com.example.ga_23s2.viewmodel.Session;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * @author Han Yan
 */
public class SearchFragment extends Fragment {
  FragmentSearchBinding binding;
  SearchHelper searchHelper;
  Session session;
  PostAdapter postAdapter;
  private EventListener<QuerySnapshot> eventListener;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentSearchBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding.searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
    session = Session.getInstance();

    // Load post and wire adapter
    PostLoader postLoader = new PostLoader(getContext(), x -> true); // get all posts
    postAdapter = new PostAdapter(getContext());
    postLoader.loadUserPosts(
        posts -> {
          DataStorageService.getInstance().setPosts(posts);
          postAdapter.filterList(posts);

          //                    eventListener = createEventListener(new ArrayList<>());
          //                    session.getUserCollection().addSnapshotListener(eventListener);

        });
    binding.searchResults.setAdapter(postAdapter);

    searchHelper = new SearchHelper(postAdapter);
    binding.searchView.setOnQueryTextListener(searchHelper.queryTextListener());
    binding.btnNewPost.setOnClickListener(
        v -> {
          Intent intent = new Intent(getContext(), AddPostActivity.class);
          startActivity(intent);
        });

    setupHideKeyboardOnTouchOutside(binding.fragmentSearch);
    setupHideKeyboardOnTouchOutside(binding.searchResults);
  }

  //    public EventListener<QuerySnapshot> createEventListener(List<Post> posts) {
  //        return (value, error) -> {
  //            if (error != null) return;
  //            if (value != null) {
  //                handleDocumentChanges(value.getDocumentChanges(), posts);
  //            }
  //        };
  //    }
  //
  //    private void handleDocumentChanges(List<DocumentChange> documentChanges, List<Post> posts) {
  //        int count = posts.size();
  //        for (DocumentChange documentChange : documentChanges) {
  //            if (documentChange.getType() == DocumentChange.Type.ADDED) {
  //                Post post = SnapshotParser.parsePost(documentChange.getDocument());
  //                posts.add(post);
  //            }
  //        }
  //        if (count == 0) {
  //            postAdapter.notifyDataSetChanged();
  //        } else {
  //            postAdapter.notifyItemRangeInserted(posts.size(), posts.size());
  //            binding.searchResults.smoothScrollToPosition(posts.size() - 1);
  //        }
  //    }

  private void setupHideKeyboardOnTouchOutside(View view) {
    view.setOnTouchListener(
        (v, event) -> {
          if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hideKeyboard(v);
          }
          return false;
        });
  }

  private void hideKeyboard(View view) {
    InputMethodManager imm =
        (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }

  @Override
  public void onPause() {
    super.onPause();
    hideKeyboard(binding.getRoot());
  }
}
