/**
 * File generated by Magnet rest2mobile 1.1 - May 8, 2015 11:30:13 PM
 * @see {@link http://developer.magnet.com}
 */

package com.jim.instagramclientandroid.api.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "count" : 184,
  "data" : [ {
    "created_time" : "1431150745",
    "text" : "@rdiaz891",
    "from" : {
      "username" : "mdiaz103",
      "profile_picture" : "https://instagramimages-a.akamaihd.net/profiles/profile_194206121_75sq_1378970369.jpg",
      "id" : "194206121",
      "full_name" : ""
    },
    "id" : "980886008683966590"
  }, {
    "created_time" : "1431151463",
    "text" : "@sovereignmoon,lookbluee",
    "from" : {
      "username" : "tuanumus",
      "profile_picture" : "https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-19/10632166_1484930175104129_1823516244_a.jpg",
      "id" : "1506829127",
      "full_name" : "TuanHa"
    ...

 */

public class Comments {

  
  private Integer count;

  
  private List<Comment> data;

  public Integer getCount() {
    return count;
  }
  public List<Comment> getData() {
    return data;
  }

  /**
  * Builder for Comments
  **/
  public static class CommentsBuilder {
    private Comments toBuild = new Comments();

    public CommentsBuilder() {
    }

    public Comments build() {
      return toBuild;
    }

    public CommentsBuilder count(Integer value) {
      toBuild.count = value;
      return this;
    }
    public CommentsBuilder data(List<Comment> value) {
      toBuild.data = value;
      return this;
    }
  }
}
