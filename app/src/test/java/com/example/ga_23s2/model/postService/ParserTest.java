package com.example.ga_23s2.model.postService;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

public class ParserTest {
  private Parser parser;

  @Test
  public void testParseWithValidQuery() {
    String query = "title contains \"God Eater\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithDateFilter() {
    String query = "date created > \"2022-01-01 00:00:00\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithBookmarkFilter() {
    String query = "bookmark > 100";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithEmptyQuery() {
    String query = "";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertTrue(entries.isEmpty());
  }

  @Test
  public void testParseWithNumericValueInTextFilter() {
    String query = "title contains 12345";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithSpecialCharactersInValue() {
    String query = "title contains \"@#$%^&*()\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithSorting() {
    String query = "title ascending";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithMultipleConditions() {
    String query = "title contains \"God Eater\" and date created > \"2022-01-01 00:00:00\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithFieldCaseSensitivity() {
    String query = "TITLE contains \"God Eater\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithOperatorCaseSensitivity() {
    String query = "title CONTAINS \"God Eater\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }

  @Test
  public void testParseWithEscapeCharactersInValue() {
    String query = "title contains \"God\\\" Eater\"";
    parser = new Parser(query);

    List<PostServiceEntry> entries = parser.parse();

    assertNotNull(entries);
    assertFalse(entries.isEmpty());
  }
}
