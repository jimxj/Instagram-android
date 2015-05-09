/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jim.instagramclientandroid.api.model.beans.Comment;
import com.jim.instagramclientandroid.api.model.beans.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends ArrayAdapter<Photo> {
  // View lookup cache
  private static class ViewHolder {
    SimpleDraweeView authorImage;
    TextView userName;
    TextView postTime;
    SimpleDraweeView postImage;
    TextView likes;
    TextView commentNum;
    ListView recentComments;
  }


  public PhotosAdapter(Context context, List<Photo> objects) {
    super(context, R.layout.item_photo, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Photo photo = getItem(position);

    ViewHolder viewHolder = null;
    if(null == convertView) {
      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
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

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    if(null != photo.getUser().getProfile_picture()){
      viewHolder.authorImage.setImageURI(Uri.parse(photo.getUser().getProfile_picture()));
    } else {
      viewHolder.authorImage.setImageURI(Uri.parse("res:///" + R.drawable.default_profile_image));
    }

    viewHolder.postImage.setImageURI(Uri.parse(photo.getImages().getStandard_resolution().getUrl()));

    viewHolder.postTime.setText(Utils.toRelativeTime(photo.getCreated_time()));

    viewHolder.userName.setText(photo.getUser().getUsername());

    viewHolder.likes.setText(Utils.formatInt(photo.getLikes().getCount()));

    viewHolder.commentNum.setText("View all " + Utils.formatInt(photo.getComments().getCount()) + " comments");

    CommentAdapter commentAdapter = (CommentAdapter) viewHolder.recentComments.getAdapter();
    commentAdapter.clear();
    commentAdapter.addAll(photo.getComments().getData().size() < 3 ? photo.getComments().getData() : photo.getComments().getData().subList(0, 2));
    commentAdapter.notifyDataSetChanged();
    Utils.setListViewHeightBasedOnChildren(viewHolder.recentComments);

    return convertView;
  }
}
