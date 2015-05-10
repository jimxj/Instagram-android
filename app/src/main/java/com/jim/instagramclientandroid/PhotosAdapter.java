/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jim.instagramclientandroid.api.InstagramApi;
import com.jim.instagramclientandroid.api.model.beans.Comment;
import com.jim.instagramclientandroid.api.model.beans.MediaCommentsResult;
import com.jim.instagramclientandroid.api.model.beans.Photo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotosAdapter extends ArrayAdapter<Photo> {
  public static final String TAG = "PhotosAdapter";
  // View lookup cache
  private static class ViewHolder {
    TextView caption;
    SimpleDraweeView authorImage;
    TextView userName;
    TextView postTime;
    SimpleDraweeView postImage;
    TextView likes;
    TextView commentNum;
    ListView recentComments;
    ImageView payIcon;
    VideoView videoView;
  }

  private InstagramApi instagramApi;

  public PhotosAdapter(Context context, List<Photo> objects, InstagramApi instagramApi) {
    super(context, R.layout.item_photo, objects);
    this.instagramApi = instagramApi;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final Photo photo = getItem(position);

    final ViewHolder viewHolder = null == convertView ? new ViewHolder() :  (ViewHolder) convertView.getTag();
    if(null == convertView) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

      viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
      viewHolder.authorImage = (SimpleDraweeView) convertView.findViewById(R.id.ivProfileImage);
      viewHolder.userName = (TextView) convertView.findViewById(R.id.tvUserName);
      viewHolder.postTime = (TextView) convertView.findViewById(R.id.tvTime);
      viewHolder.postImage = (SimpleDraweeView) convertView.findViewById(R.id.ivPhoto);
      viewHolder.likes = (TextView) convertView.findViewById(R.id.tvLikeNum);
      viewHolder.commentNum = (TextView) convertView.findViewById(R.id.tvViewAllComments);
      viewHolder.recentComments = (ListView) convertView.findViewById(R.id.lvRecentComments);
      viewHolder.recentComments.setDivider(null);
      CommentAdapter commentAdapter = new CommentAdapter(getContext(), new ArrayList<Comment>());
      viewHolder.recentComments.setAdapter(commentAdapter);
      viewHolder.payIcon = (ImageView) convertView.findViewById(R.id.ivPlay);
      //viewHolder.videoView = (VideoView) convertView.findViewById(R.id.vvVideo);

      convertView.setTag(viewHolder);
    }

    if(null != photo.getUser().getProfile_picture()){
      viewHolder.authorImage.setImageURI(Uri.parse(photo.getUser().getProfile_picture()));
    } else {
      viewHolder.authorImage.setImageURI(Uri.parse("res:///" + R.drawable.default_profile_image));
    }

    if(null != photo.getCaption()) {
      viewHolder.caption.setVisibility(View.VISIBLE);
      viewHolder.caption.setText(photo.getCaption().getText());
    } else {
      viewHolder.caption.setVisibility(View.GONE);
    }

    viewHolder.postImage.setImageURI(Uri.parse(photo.getImages().getStandard_resolution().getUrl()));

    viewHolder.postTime.setText(Utils.toRelativeTime(photo.getCreated_time()));

    viewHolder.userName.setText(photo.getUser().getUsername());

    viewHolder.likes.setText(Utils.formatInt(photo.getLikes().getCount()) + " likes");

    viewHolder.commentNum.setText("View all " + Utils.formatInt(photo.getComments().getCount()) + " comments");
    viewHolder.commentNum.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        instagramApi.getMediaComments(photo.getId(), InstagramApi.INSTAGRAM_CLIENT_ID, new Callback<MediaCommentsResult>() {
          @Override
          public void success(MediaCommentsResult commentsResutl, Response response) {
            Log.d(TAG, "instagramApi.getMediaComments returns : " + commentsResutl.getData().size());
            FragmentManager fm = ((Activity) getContext()).getFragmentManager();
            String title = null;
            if (null != photo.getCaption()) {
              title = photo.getCaption().getText();
            } else {
              title = "Comments";
            }
            CommentsDialogFragment diag = CommentsDialogFragment.newInstance(commentsResutl.getData(), title);
            if (diag.getDialog() != null) {
              diag.getDialog().setCanceledOnTouchOutside(true); // after fragment has already dialog, i. e. in onCreateView()
            }
            diag.show(fm, "Comments");
          }

          @Override
          public void failure(RetrofitError retrofitError) {
            Log.e(TAG, "Failed to call instagramApi.getMediaComments, error : " + retrofitError.getLocalizedMessage());
          }
        });
      }
    });

    //viewHolder.videoView.setVisibility(View.GONE);
    if(photo.getType().equals("video")) {
      viewHolder.payIcon.setVisibility(View.VISIBLE);
      viewHolder.payIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          FragmentManager fm = ((Activity) getContext()).getFragmentManager();
          VideoDialogFragment diag = VideoDialogFragment.newInstance(photo.getVideos().getStandard_resolution().getUrl());
          if (diag.getDialog() != null) {
            diag.getDialog().setCanceledOnTouchOutside(true); // after fragment has already dialog, i. e. in onCreateView()
          }
          diag.show(fm, null != photo.getCaption() ? photo.getCaption().getText() : "");
//          viewHolder.videoView.setVisibility(View.VISIBLE);
//          viewHolder.videoView.setVideoURI(Uri.parse(photo.getVideos().getStandard_resolution().getUrl()));
//          viewHolder.videoView.setMediaController(new MediaController(getContext()));
//          viewHolder.videoView.requestFocus();
//          viewHolder.videoView.start();
        }
      });
    } else {
      viewHolder.payIcon.setVisibility(View.GONE);
    }

    CommentAdapter commentAdapter = (CommentAdapter) viewHolder.recentComments.getAdapter();
    commentAdapter.clear();
    commentAdapter.addAll(photo.getComments().getData().size() < 3 ? photo.getComments().getData() : photo.getComments().getData().subList(0, 2));
    commentAdapter.notifyDataSetChanged();
    Utils.setListViewHeightBasedOnChildren(viewHolder.recentComments);

    return convertView;
  }
}
