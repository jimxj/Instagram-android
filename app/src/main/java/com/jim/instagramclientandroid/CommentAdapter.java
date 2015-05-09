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
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jim.instagramclientandroid.api.model.beans.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
  // View lookup cache
  private static class ViewHolder {
    SimpleDraweeView authorImage;
    TextView userName;
    TextView postTime;
    TextView commentContent;
  }


  public CommentAdapter(Context context, List<Comment> objects) {
    super(context, R.layout.item_comment, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Comment comment = getItem(position);

    ViewHolder viewHolder = null;
    if(null == convertView) {
      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
      viewHolder.authorImage = (SimpleDraweeView) convertView.findViewById(R.id.ivCommentUserImage);
      viewHolder.userName = (TextView) convertView.findViewById(R.id.tvCommentUserName);
      viewHolder.postTime = (TextView) convertView.findViewById(R.id.tvCommentTime);
      viewHolder.commentContent = (TextView) convertView.findViewById(R.id.tvCommentContent);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.postTime.setText(Utils.toRelativeTime(comment.getCreated_time()));

    viewHolder.userName.setText(comment.getFrom().getUsername());

    viewHolder.commentContent.setText(comment.getText());

    viewHolder.authorImage.setImageURI(Uri.parse(comment.getFrom().getProfile_picture()));

    return convertView;
  }
}
