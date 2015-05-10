/**
 * File generated by Magnet rest2mobile 1.1 - May 8, 2015 11:30:13 PM
 * @see {@link http://developer.magnet.com}
 */

package com.jim.instagramclientandroid.api.model.beans;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Generated from json example
{
  "username" : "mdiaz103",
  "profile_picture" : "https://instagramimages-a.akamaihd.net/profiles/profile_194206121_75sq_1378970369.jpg",
  "id" : "194206121",
  "full_name" : ""
}

 */

public class From implements Parcelable {

  
  private String full_name;

  
  private String id;

  
  private String profile_picture;

  
  private String username;

  public String getFull_name() {
    return full_name;
  }
  public String getId() {
    return id;
  }
  public String getProfile_picture() {
    return profile_picture;
  }
  public String getUsername() {
    return username;
  }

  public From() {}

  public From(Parcel in) {
    this.full_name = in.readString();
    this.id = in.readString();
    this.profile_picture = in.readString();
    this.username = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(full_name);
    parcel.writeString(id);
    parcel.writeString(profile_picture);
    parcel.writeString(username);
  }

  /**
  * Builder for From
  **/
  public static class FromBuilder {
    private From toBuild = new From();

    public FromBuilder() {
    }

    public From build() {
      return toBuild;
    }

    public FromBuilder full_name(String value) {
      toBuild.full_name = value;
      return this;
    }
    public FromBuilder id(String value) {
      toBuild.id = value;
      return this;
    }
    public FromBuilder profile_picture(String value) {
      toBuild.profile_picture = value;
      return this;
    }
    public FromBuilder username(String value) {
      toBuild.username = value;
      return this;
    }
  }
}
