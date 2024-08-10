package com.example.ga_23s2.model.postService;

import com.example.ga_23s2.model.post.Post;
import com.example.ga_23s2.model.postService.Tokens.FieldToken;
import com.example.ga_23s2.model.postService.Tokens.FilterToken;
import com.example.ga_23s2.model.postService.Tokens.OperatorToken;
import com.example.ga_23s2.model.postService.Tokens.SortToken;
import com.example.ga_23s2.model.postService.Tokens.Token;
import com.example.ga_23s2.model.postService.Tokens.ValueToken;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parser {

  List<Token> tokens;
  List<String> values;
  private int index = 0;
  private int indexV = 0;

  public Parser(List<Token> tokens, List<String> values) {
    this.tokens = tokens;
    this.values = values;
  }

  public Parser(String query) {
    Tokeniser tokeniser = new Tokeniser(query);
    this.tokens = tokeniser.getTokens();
    this.values = tokeniser.getValues();
  }

  /** Tokenisation format: <Field> <Operator> <Value>(if needed) */
  public List<PostServiceEntry> parse() {
    List<PostServiceEntry> postServiceEntries = new ArrayList<>();
    index = 0;
    parseHelper(postServiceEntries);
    return postServiceEntries;
  }

  public void parseHelper(List<PostServiceEntry> entries) {
    if (index >= tokens.size()) return;
    // Field
    PostServiceEntry entry = new PostServiceEntry();
    if (tokens.size() == 1) {
      entry.setPredicate(new FilterService().anywhereContains(values.get(0)));
      entries.add(entry);
      return;
    }
    FieldToken f = FieldToken.ANY_FILED;
    OperatorToken o = FilterToken.CONTAINS;
    ValueToken v = ValueToken.TEXT;
    for (; index < tokens.size(); index++) {
      Token token = tokens.get(index);
      if (token instanceof FieldToken) f = (FieldToken) token;
      else if (token instanceof OperatorToken) o = (OperatorToken) token;
      else {
        v = (ValueToken) token;
        indexV = index;
        break;
      }
    }
    // parse into sorting services
    if (o instanceof SortToken) entries.add(parseSort(f, o));
    else if (f == FieldToken.DATE_MODIFIED || f == FieldToken.DATE_CREATED) {
      entry = parseFilterDate(f, o, v);
      if (entry != null) entries.add(entry);
    } else if (f == FieldToken.BOOKMARK_NUMBER) entries.add(parseFilterBookmark(o, v));
    else entries.add(parseContains(f, v));
  }

  private PostServiceEntry parseSort(FieldToken f, OperatorToken o) {
    if (!(o instanceof SortToken)) return null;
    if (f == FieldToken.ANY_FILED) return null;
    PostServiceEntry entry = new PostServiceEntry();
    entry.setPriority(true);
    if (f == FieldToken.DATE_MODIFIED) {
      entry.setSortComparator(Comparator.comparing(Post::getModifiedDate));
    } else if (f == FieldToken.DATE_CREATED) {
      entry.setSortComparator(Comparator.comparing(Post::getDateOfCreation));
    } else if (f == FieldToken.BOOKMARK_NUMBER) {
      entry.setSortComparator(Comparator.comparing(Post::getBookmarked));
    } else entry.setSortComparator(Comparator.comparing(f.getConsumer()));
    if (o == SortToken.DESC) entry.reverseComparator();
    return entry;
  }

  private PostServiceEntry parseFilterDate(FieldToken f, OperatorToken o, ValueToken v) {
    if (!(o instanceof FilterToken)) return null;
    PostServiceEntry entry = new PostServiceEntry();

    Timestamp targetTime = parseStringToTime(values.get(indexV));
    if (f == FieldToken.DATE_MODIFIED) {
      entry.setPredicate(post -> post.getModifiedDate().after(targetTime));
      entry.setSortComparator(Comparator.comparing(Post::getModifiedDate));
    } else {
      entry.setPredicate(post -> post.getDateOfCreation().after(targetTime));
      entry.setSortComparator(Comparator.comparing(Post::getDateOfCreation));
    }
    if (o == FilterToken.SMALLER) entry.negatePredicate();
    return entry;
  }

  private Timestamp parseStringToTime(String s) {
    return null; // TODO: implementation
  }

  private PostServiceEntry parseFilterBookmark(OperatorToken o, ValueToken v) {
    PostServiceEntry entry = new PostServiceEntry();
    int target = Integer.parseInt(values.get(indexV));
    if (o == FilterToken.GREATER) entry.setPredicate(post -> post.getBookmarked() > target);
    else if (o == FilterToken.EQUAL) entry.setPredicate(post -> post.getBookmarked() == target);
    else entry.setPredicate(post -> post.getBookmarked() < target);
    return entry;
  }

  private PostServiceEntry parseContains(FieldToken f, ValueToken v) {
    PostServiceEntry entry = new PostServiceEntry();
    if (f == FieldToken.ANY_FILED)
      entry.setPredicate(new FilterService().anywhereContains(values.get(indexV)));
    else {
      entry.setPredicate(x -> f.getConsumer().apply(x).contains(values.get(indexV)));
    }
    return entry;
  }
}
