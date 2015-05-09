/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jim.instagramclientandroid.api.model.beans.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends ArrayAdapter<Photo> {

  public PhotosAdapter(Context context, List<Photo> objects) {
    super(context, R.layout.item_photo, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Photo photo = getItem(position);

    if(null == convertView) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
    }

    TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
    tvTime.setText(Utils.toRelativeTime(photo.getCreated_time()));

    TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
    tvUserName.setText(photo.getUser().getFull_name());

    TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikeNum);
    tvLikes.setText(Utils.formatInt(photo.getLikes().getCount()));

    TextView tvViewAllComments = (TextView) convertView.findViewById(R.id.tvViewAllComments);
    tvViewAllComments.setText("View all " + Utils.formatInt(photo.getComments().getCount()) + " comments");

//    ImageView userProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
//    Picasso.with(getContext())
//            .load(photo.getUser().getProfile_picture())
//            .resize(40, 40)
//            .centerCrop()
//            .into(userProfileImage);
    SimpleDraweeView userProfileImage = (SimpleDraweeView) convertView.findViewById(R.id.ivProfileImage);
    userProfileImage.setImageURI(Uri.parse(photo.getUser().getProfile_picture()));

    SimpleDraweeView postImage = (SimpleDraweeView) convertView.findViewById(R.id.ivPhoto);
    postImage.setImageURI(Uri.parse(photo.getImages().getStandard_resolution().getUrl()));

//    ImageView photoImage = (ImageView) convertView.findViewById(R.id.ivPhoto);
//    Picasso.with(getContext())
//            .load(photo.getImages().getStandard_resolution().getUrl())
//            .into(photoImage);

    return convertView;
  }
}
