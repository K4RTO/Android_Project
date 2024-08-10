package com.example.ga_23s2.model.Tools;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ParseString {
  public static Timestamp toTimestamp(String s) {
    s = prettify(s);
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    if (Integer.valueOf(s.substring(0, 4)) < 1800) throw new InvalidDateRangeException(s);
    return new Timestamp(f.parse(s, new ParsePosition(0)).getTime());
  }

  private static String prettify(String s) {
    if (s.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) return s;
    if (s.matches("\\d{2,4}-\\d{1,2}-\\d{1,2}")) {
      int yy = Calendar.getInstance().get(Calendar.YEAR) % 2000;
      if (Integer.parseInt(s.substring(0, 2)) < yy) return "20" + s;
      else return "19" + s;
    } else throw new InvalidDateStringException(s);
  }
}
