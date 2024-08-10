package com.example.ga_23s2.model.Tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.junit.Test;

public class ParseStringTest {

  @Test
  public void testToTimestampWithValidDate() {
    String date = "2022-10-18";
    Timestamp expected =
        new Timestamp(
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(date, new ParsePosition(0))
                .getTime());

    Timestamp actual = ParseString.toTimestamp(date);

    assertEquals(expected, actual);
  }

  @Test
  public void testToTimestampWithShortYear() {
    String date = "22-10-18";
    String expectedDate = "2022-10-18";
    Timestamp expected =
        new Timestamp(
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(expectedDate, new ParsePosition(0))
                .getTime());

    Timestamp actual = ParseString.toTimestamp(date);

    assertEquals(expected, actual);
  }

  @Test
  public void testToTimestampWithInvalidDate() {
    String date = "invalid-date";

    assertThrows(InvalidDateStringException.class, () -> ParseString.toTimestamp(date));
  }

  @Test
  public void testToTimestampWithEmptyDate() {
    String date = "";

    assertThrows(InvalidDateStringException.class, () -> ParseString.toTimestamp(date));
  }

  @Test
  public void testToTimestampWithNullDate() {
    assertThrows(NullPointerException.class, () -> ParseString.toTimestamp(null));
  }

  @Test
  public void testToTimestampWithYearOnly() {
    String date = "2022";
    assertThrows(InvalidDateStringException.class, () -> ParseString.toTimestamp(date));
  }

  @Test
  public void testToTimestampWithFutureDate() {
    String date = "2030-10-18";
    Timestamp expected =
        new Timestamp(
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(date, new ParsePosition(0))
                .getTime());

    Timestamp actual = ParseString.toTimestamp(date);

    assertEquals(expected, actual);
  }

  @Test
  public void testToTimestampWithLeapYear() {
    String date = "2024-02-29";
    Timestamp expected =
        new Timestamp(
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(date, new ParsePosition(0))
                .getTime());

    Timestamp actual = ParseString.toTimestamp(date);

    assertEquals(expected, actual);
  }
}
