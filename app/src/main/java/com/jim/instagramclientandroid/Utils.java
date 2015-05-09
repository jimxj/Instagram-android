/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.text.format.DateUtils;

import java.text.NumberFormat;

public class Utils {

  private static NumberFormat numberFormat = NumberFormat.getInstance();

  public static String formatInt(int i) {
    return numberFormat.format(i);
  }

  public static String toRelativeTime(String unixTimestampInStr) {
    String result = DateUtils.getRelativeTimeSpanString(Long.parseLong(unixTimestampInStr) * 1000).toString();
    result = result.replace(" ago", "");
    result = result.replaceAll("minutes", "m");
    result = result.replaceAll("hours", "h");
    result = result.replaceAll("days", "d");
    result = result.replaceAll("weeks", "w");
    result = result.replaceAll("years", "y");

    return result;
  }



}
