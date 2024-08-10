package com.example.ga_23s2.model.postService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.ga_23s2.model.postService.Tokens.FieldToken;
import com.example.ga_23s2.model.postService.Tokens.FilterToken;
import com.example.ga_23s2.model.postService.Tokens.SortToken;
import com.example.ga_23s2.model.postService.Tokens.Token;
import com.example.ga_23s2.model.postService.Tokens.ValueToken;
import java.util.List;
import org.junit.Test;

public class TokeniserTest {
  private Tokeniser tokeniser;

  @Test
  public void testTokeniseWithMixedCase() {
    String query = "Title Ascending \"God Eater\"";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertEquals(3, tokens.size());
    assertEquals(3, values.size());

    assertTrue(tokens.contains(FieldToken.TITLE));
    assertTrue(tokens.contains(SortToken.ASC));
    assertTrue(tokens.contains(ValueToken.TEXT));

    assertTrue(values.contains("title"));
    assertTrue(values.contains("ascending"));
    assertTrue(values.contains("god eater"));
  }

  @Test
  public void testTokeniseWithMultipleOperators() {
    String query = "date created > 2020 and < 2022";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertTrue(tokens.contains(FilterToken.GREATER));
    assertTrue(tokens.contains(FilterToken.SMALLER));
    assertTrue(tokens.contains(ValueToken.NUMERIC));

    assertTrue(values.contains("2020"));
    assertTrue(values.contains("2022"));
  }

  @Test
  public void testTokeniseWithValidQuery() {
    String query = "title ascending \"God Eater\"";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertEquals(3, tokens.size());
    assertEquals(3, values.size());

    assertTrue(tokens.contains(FieldToken.TITLE));
    assertTrue(tokens.contains(SortToken.ASC));
    assertTrue(tokens.contains(ValueToken.TEXT));

    assertTrue(values.contains("title"));
    assertTrue(values.contains("ascending"));
    assertTrue(values.contains("god eater"));
  }

  @Test
  public void testTokeniseWithEmptyQuery() {
    String query = "";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertTrue(tokens.isEmpty());
    assertTrue(values.isEmpty());
  }

  @Test
  public void testTokeniseWithOperators() {
    String query = "date created > 2022";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertEquals(5, tokens.size());
    assertEquals(4, values.size());

    assertTrue(tokens.contains(FilterToken.GREATER));
    assertTrue(tokens.contains(ValueToken.NUMERIC));
  }

  @Test
  public void testTokeniseWithInvalidTokens() {
    String query = "invalidToken anotherInvalidToken";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertEquals(2, tokens.size());
    assertEquals(2, values.size());

    assertTrue(tokens.contains(ValueToken.TEXT));
    assertFalse(tokens.contains(FieldToken.TITLE));

    assertTrue(values.contains("invalidtoken"));
    assertTrue(values.contains("anotherinvalidtoken"));
  }

  @Test
  public void testTokeniseNumericValue() {
    String query = "bookmark > 100";
    tokeniser = new Tokeniser(query);

    List<Token> tokens = tokeniser.getTokens();
    List<String> values = tokeniser.getValues();

    assertTrue(tokens.contains(FieldToken.BOOKMARK_NUMBER));
    assertTrue(tokens.contains(FilterToken.GREATER));
    assertTrue(tokens.contains(ValueToken.NUMERIC));

    assertTrue(values.contains("bookmark"));
    assertTrue(values.contains(">"));
    assertTrue(values.contains("100"));
  }
}
