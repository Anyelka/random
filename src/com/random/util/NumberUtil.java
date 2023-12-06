package com.random.util;

public class NumberUtil {
  public static boolean isInt(char c) {
    try {
      Integer.parseInt(c + "");
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
