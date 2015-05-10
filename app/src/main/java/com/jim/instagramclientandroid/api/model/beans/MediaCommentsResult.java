/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid.api.model.beans;

import java.util.List;

public class MediaCommentsResult {
  private Meta meta;
  private List<Comment> data;

  public Meta getMeta() {
    return meta;
  }

  public List<Comment> getData() {
    return data;
  }
}
