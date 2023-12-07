package com.random.util;

public class NumberUtil {
  public static boolean isInt(char c) {
    return isInt("" + c);
  }


  public static boolean isInt(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isLong(String s) {
    try {
      Long.parseLong(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
