package com.example.ga_23s2.model.postService;

import com.example.ga_23s2.model.postService.Tokens.FieldToken;
import com.example.ga_23s2.model.postService.Tokens.FilterToken;
import com.example.ga_23s2.model.postService.Tokens.SortToken;
import com.example.ga_23s2.model.postService.Tokens.Token;
import com.example.ga_23s2.model.postService.Tokens.ValueToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Tokeniser {
  int quoteCount = 0;
  List<Token> tokens;
  List<String> values;
  private final String[] words;
  private static final Map<String, Token> map;

  static {
    map = new HashMap<>(20);
    map.put("ascending", SortToken.ASC);
    map.put("descending", SortToken.DESC);
    map.put("after", FilterToken.GREATER);
    map.put("before", FilterToken.SMALLER);
    map.put("smaller", FilterToken.SMALLER);
    map.put("greater", FilterToken.SMALLER);
    map.put("larger", FilterToken.SMALLER);
    map.put("contains", FilterToken.CONTAINS);
    map.put("title", FieldToken.TITLE);
    map.put("author", FieldToken.AUTHOR);
    map.put("writer", FieldToken.AUTHOR);
    map.put("contents", FieldToken.CONTENTS);
    map.put("game_name", FieldToken.GAME_NAME);
    map.put("platform", FieldToken.PLATFORM);
    map.put("date created", FieldToken.DATE_CREATED);
    map.put("date modified", FieldToken.DATE_MODIFIED);
    map.put("bookmark", FieldToken.BOOKMARK_NUMBER);
  }

  public Tokeniser(String query) {
    this.words = query.split("\\s+");
    this.tokens = new ArrayList<>(words.length / 2);
    this.values = new ArrayList<>(words.length / 2);
    if (query.length() > 0) tokenise();
  }

  public List<String> getValues() {
    return values;
  }

  public List<Token> getTokens() {
    return tokens;
  }

  public void tokenise() {
    StringBuilder builder = new StringBuilder();
    for (String s : words) {
      if (s.length() == 0) continue;
      // words surrounded by quotation
      String word = s.toLowerCase(Locale.ROOT);
      if (word.equals("of")) continue;
      if (word.charAt(0) == '"') {
        quoteCount++;
        if (word.length() > 1) builder.append(word.substring(1));
        continue;
      }
      if (quoteCount > 0) {
        if (builder.length() > 0) builder.append(" ");
        builder.append(word);
        if (word.charAt(word.length() - 1) == '"') {
          quoteCount -= 1;
          String cleanedValue = builder.toString().substring(0, builder.length() - 1);
          values.add(cleanedValue);
          tokens.add(ValueToken.TEXT);
          builder = new StringBuilder();
        }
        continue;
      }
      // operators
      else if (word.equals(">")) tokens.add(FilterToken.GREATER);
      else if (word.equals("<")) tokens.add(FilterToken.SMALLER);
      else if (word.equals("=")) tokens.add(FilterToken.EQUAL);

      if (Character.isDigit(word.charAt(0))) tokens.add(ValueToken.NUMERIC);
      else if (word.equals("asc")) tokens.add(SortToken.ASC);
      else if (word.equals("desc")) tokens.add(SortToken.DESC);
      else {
        boolean found = false;
        for (String key : map.keySet()) {
          if (isSimilarWith(word, key)) {
            tokens.add(map.get(key));
            found = true;
            break;
          }
        }
        if (!found) {
          tokens.add(ValueToken.TEXT);
        }
      }
      values.add(word);
    }
  }

  private double similarWith(String s, String target) {
    StringSimilarity test = new StringSimilarity(s, target);
    return test.getSimilarity();
  }

  private boolean isSimilarWith(String s, String target) {
    return similarWith(s, target) >= 0.7;
  }
  //
  //  private boolean isCreation(String s) {
  //    return isSimilarWith(s, "creation date")
  //        || isSimilarWith(s, "created")
  //        || isSimilarWith(s, "date of creation")
  //        || isSimilarWith(s, "date created");
  //  }
  //
  //  private boolean isModificationDate(String s) {
  //    return isSimilarWith(s, "modification date")
  //        || isSimilarWith(s, "modified")
  //        || isSimilarWith(s, "date of modification")
  //        || isSimilarWith(s, "date modified");
  //  }
}
