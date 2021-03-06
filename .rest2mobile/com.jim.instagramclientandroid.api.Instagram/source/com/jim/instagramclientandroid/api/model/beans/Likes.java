/**
 * File generated by Magnet rest2mobile 1.1 - May 8, 2015 11:30:13 PM
 * @see {@link http://developer.magnet.com}
 */

package com.jim.instagramclientandroid.api.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "count" : 25875,
  "data" : [ {
    "username" : "diegoroyot",
    "profile_picture" : "https://instagramimages-a.akamaihd.net/profiles/anonymousUser.jpg",
    "id" : "2137656897",
    "full_name" : "DIEGO"
  }, {
    "username" : "a7bood7811",
    "profile_picture" : "https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xpa1/t51.2885-19/11205907_1567192060208148_778099661_a.jpg",
    "id" : "2138441227",
    "full_name" : "Abood"
  }, {
    "username" : "sachin_pw",
    "profile_picture" : "https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/11240317_932976826754359_1223990533_a.jpg",
    "id" : "2137667805",
    "full_name" : "Sachin_@PW"
  }, {
    "username" : "cal_dearman",
    "profile_picture" : "https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xpf1/t51.2885-19/11189359_770779303017921_623662401_a.jpg",
      ...

 */

public class Likes {

  
  private Integer count;

  
  private List<Datum> data;

  public Integer getCount() {
    return count;
  }
  public List<Datum> getData() {
    return data;
  }

  /**
  * Builder for Likes
  **/
  public static class LikesBuilder {
    private Likes toBuild = new Likes();

    public LikesBuilder() {
    }

    public Likes build() {
      return toBuild;
    }

    public LikesBuilder count(Integer value) {
      toBuild.count = value;
      return this;
    }
    public LikesBuilder data(List<Datum> value) {
      toBuild.data = value;
      return this;
    }
  }
}
