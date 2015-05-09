/**
 * File generated by Magnet rest2mobile 1.1 - May 8, 2015 11:30:13 PM
 * @see {@link http://developer.magnet.com}
 */

package com.jim.instagramclientandroid.api.model.beans;


/**
 * Generated from json example
{
  "low_bandwidth" : {
    "url" : "https://scontent.cdninstagram.com/hphotos-xaf1/t50.2886-16/11235676_636913146440099_1431364268_s.mp4",
    "width" : 480,
    "height" : 480
  },
  "standard_resolution" : {
    "url" : "https://scontent.cdninstagram.com/hphotos-xaf1/t50.2886-16/11237334_435195373272455_1360998231_n.mp4",
    "width" : 640,
    "height" : 640
  },
  "low_resolution" : {
    "url" : "https://scontent.cdninstagram.com/hphotos-xaf1/t50.2886-16/11235676_636913146440099_1431364268_s.mp4",
    "width" : 480,
    "height" : 480
  }
}

 */

public class Videos {

  
  private Low_bandwidth low_bandwidth;

  
  private Low_resolution low_resolution;

  
  private Standard_resolution standard_resolution;

  public Low_bandwidth getLow_bandwidth() {
    return low_bandwidth;
  }
  public Low_resolution getLow_resolution() {
    return low_resolution;
  }
  public Standard_resolution getStandard_resolution() {
    return standard_resolution;
  }

  /**
  * Builder for Videos
  **/
  public static class VideosBuilder {
    private Videos toBuild = new Videos();

    public VideosBuilder() {
    }

    public Videos build() {
      return toBuild;
    }

    public VideosBuilder low_bandwidth(Low_bandwidth value) {
      toBuild.low_bandwidth = value;
      return this;
    }
    public VideosBuilder low_resolution(Low_resolution value) {
      toBuild.low_resolution = value;
      return this;
    }
    public VideosBuilder standard_resolution(Standard_resolution value) {
      toBuild.standard_resolution = value;
      return this;
    }
  }
}
