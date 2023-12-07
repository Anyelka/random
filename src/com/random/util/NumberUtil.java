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
}
