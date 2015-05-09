/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

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
    result = result.replaceAll("minute", "m");
    result = result.replaceAll("hour", "h");
    result = result.replaceAll("day", "d");
    result = result.replaceAll("week", "w");
    result = result.replaceAll("year", "y");

    return result;
  }

  // http://stackoverflow.com/questions/9833834/android-listview-only-show-the-first-result
  // http://nex-otaku-en.blogspot.com/2010/12/android-put-listview-in-scrollview.html
  public static void setListViewHeightBasedOnChildren(ListView listView) {
    ListAdapter listAdapter = listView.getAdapter();
    if (listAdapter == null) {
      // pre-condition
      return;
    }

    int totalHeight = 0;
    int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
    for (int i = 0; i < listAdapter.getCount(); i++) {
      View listItem = listAdapter.getView(i, null, listView);
      listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
      totalHeight += listItem.getMeasuredHeight();
    }

    ViewGroup.LayoutParams params = listView.getLayoutParams();
    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    listView.setLayoutParams(params);
    listView.requestLayout();
  }

}
