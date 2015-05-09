package com.jim.instagramclientandroid;

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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PhotosActivity extends ActionBarActivity {
  public static final String INSTAGRAM_CLIENT_ID = "dac6dad78dae4fac9c89f1df2b094bf9";
  public static final String TAG = "PhotosActivity";
  private RestAdapter restAdapter;
  private InstagramApi instagramApi;

  @InjectView(R.id.lvPhotos)
  ListView mLvPhotos;

  private List<Photo> photos;
  private PhotosAdapter photosAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photos);

    ButterKnife.inject(this);
    Fresco.initialize(this);

    photos = new ArrayList<Photo>();
    photosAdapter = new PhotosAdapter(this, photos);
    mLvPhotos.setAdapter(photosAdapter);

    restAdapter = new RestAdapter.Builder()
            .setEndpoint("https://api.instagram.com/v1")
            .build();

    instagramApi = restAdapter.create(InstagramApi.class);
    instagramApi.getPopularMedias(INSTAGRAM_CLIENT_ID, new Callback<PopularMediasResult>() {
      @Override
      public void success(PopularMediasResult popularMediasResult, Response response) {
        Log.d(TAG, "Got popular medias : " + popularMediasResult);
        photos.addAll(popularMediasResult.getData());
        photosAdapter.notifyDataSetChanged();
      }

      @Override
      public void failure(RetrofitError error) {
        Log.e(TAG, "Failed to get popular medias : " + error);
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
