/**
 * Copyright (c) 2012-2015 Magnet Systems. All rights reserved.
 */
package com.jim.instagramclientandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jim.instagramclientandroid.api.model.beans.Comment;

import java.util.ArrayList;
import java.util.List;


public class CommentsDialogFragment extends DialogFragment {

  public static final String KEY_COMMENTS = "comments";
  public static final String KEY_TITLE = "title";

  ListView lvComments;

  static CommentsDialogFragment newInstance(List<Comment> comments, String title) {
    CommentsDialogFragment fragment = new CommentsDialogFragment();

    Bundle args = new Bundle();
    args.putString(KEY_TITLE, title);
    args.putParcelableArrayList(KEY_COMMENTS, (ArrayList) comments);
    fragment.setArguments(args);

    return fragment;
  }

  //http://stackoverflow.com/questions/13257038/custom-layout-for-dialogfragment-oncreateview-vs-oncreatedialog
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                           Bundle savedInstanceState) {
//    super.onCreateView(inflater, container, savedInstanceState);
//
//    View view = inflater.inflate(R.layout.fragement_comments, container);
//    lvComments = (ListView) view.findViewById(R.id.lvComments);
//
//    List<Comment> comments = getArguments().getParcelableArrayList(KEY_COMMENTS);
//    CommentAdapter commentAdapter = new CommentAdapter(getActivity(), comments);
//    lvComments.setAdapter(commentAdapter);
//
//    return view;
//  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    LayoutInflater i = getActivity().getLayoutInflater();
    View view = i.inflate(R.layout.fragement_comments, null);
    lvComments = (ListView) view.findViewById(R.id.lvComments);

    List<Comment> comments = getArguments().getParcelableArrayList(KEY_COMMENTS);
    CommentAdapter commentAdapter = new CommentAdapter(getActivity(), comments);
    lvComments.setAdapter(commentAdapter);

    return new AlertDialog.Builder(getActivity())
            .setView(view)
            .setTitle(getArguments().getString(KEY_TITLE))
            .setCancelable(true)
            .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                      }
                    }
            )
            .create();
  }
}
