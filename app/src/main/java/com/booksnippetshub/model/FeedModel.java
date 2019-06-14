package com.booksnippetshub.model;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class FeedModel {

    int id;
    int userid;
    String nickname;
    String time;

    String avatarUrl;
    String backgroundmageurl;

    String bookcontent;
    String bookcomment;

    int bookid;
    String bookname;

    String from;

    boolean isFolded = true;
    boolean isfollow;

    boolean isforward;
    boolean isliked;

    int likecount;
    int commentcount;

}
