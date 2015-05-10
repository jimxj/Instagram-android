package com.jim.instagramclientandroid;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jim.instagramclientandroid.api.InstagramApi;
import com.jim.instagramclientandroid.api.model.beans.Photo;
import com.jim.instagramclientandroid.api.model.beans.PopularMediasResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PhotosActivity extends ActionBarActivity {

  public static final String TAG = "PhotosActivity";
  private RestAdapter restAdapter;
  private InstagramApi instagramApi;

  @InjectView(R.id.lvPhotos)
  ListView mLvPhotos;

  @InjectView(R.id.swipeContainer)
  SwipeRefreshLayout swipeContainer;

  private PhotosAdapter photosAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photos);

    ButterKnife.inject(this);
    Fresco.initialize(this);

    restAdapter = new RestAdapter.Builder()
            .setEndpoint("https://api.instagram.com/v1")
            .build();

    instagramApi = restAdapter.create(InstagramApi.class);

    photosAdapter = new PhotosAdapter(this, new ArrayList<Photo>(), instagramApi);
    mLvPhotos.setAdapter(photosAdapter);

    // Setup refresh listener which triggers new data loading
    swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        // Your code to refresh the list here.
        // Make sure you call swipeContainer.setRefreshing(false)
        // once the network request has completed successfully.
        fetchPhotosAsync(0);
      }
    });
    // Configure the refreshing colors
    swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);


    fetchPhotosAsync(0);
  }

  private void fetchPhotosAsync(long id) {
    instagramApi.getPopularMedias(InstagramApi.INSTAGRAM_CLIENT_ID, new Callback<PopularMediasResult>() {
      @Override
      public void success(PopularMediasResult popularMediasResult, Response response) {
        Log.d(TAG, "Got popular medias : " + popularMediasResult);
        photosAdapter.clear();
        photosAdapter.addAll(popularMediasResult.getData());
        photosAdapter.notifyDataSetChanged();

        swipeContainer.setRefreshing(false);
      }

      @Override
      public void failure(RetrofitError error) {
        Log.e(TAG, "Failed to get popular medias : " + error);
        swipeContainer.setRefreshing(false);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_photos, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
