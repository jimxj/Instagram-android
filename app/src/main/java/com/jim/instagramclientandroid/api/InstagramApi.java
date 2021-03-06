/**
 * File generated by Magnet rest2mobile 1.1 - May 8, 2015 11:30:13 PM
 * @see {@link http://developer.magnet.com}
 */

package com.jim.instagramclientandroid.api;


import com.jim.instagramclientandroid.api.model.beans.*;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface InstagramApi {
  public static final String INSTAGRAM_CLIENT_ID = "dac6dad78dae4fac9c89f1df2b094bf9";

  @GET("/media/popular")
  void getPopularMedias(@Query("client_id") String client_id, Callback<PopularMediasResult> cb);

  @GET("/media/{mediaId}/comments")
  void getMediaComments(@Path("mediaId") String mediaId, @Query("client_id") String client_id, Callback<MediaCommentsResult> cb);

}
