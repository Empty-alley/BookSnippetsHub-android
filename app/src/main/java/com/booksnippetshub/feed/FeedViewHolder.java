package com.booksnippetshub.feed;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booksnippetshub.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class FeedViewHolder extends RecyclerView.ViewHolder {


    private int id;
    private int userid;
    private boolean isfollow;

    boolean isliked;

    int likecount;
    int commentcount;

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public boolean isIsfollow() {
        return isfollow;
    }

    public void setIsfollow(boolean isfollow) {
        this.isfollow = isfollow;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    Button likebtn;
    Button commentbtn;
    Button orwardbtn;
    Button followbtn;

    TextView feedcontenttext;
    TextView feedcommenttext;
    TextView nicknametext;
    TextView datetext;

    TextView bookname;

    TextView feedlikecount;
    TextView feedpingluncount;

    SimpleDraweeView avatar;

    LinearLayout feedcontentlayout;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        bookname = itemView.findViewById(R.id.bookname);
        feedlikecount = itemView.findViewById(R.id.feedlikecount);
        feedpingluncount = itemView.findViewById(R.id.feedpingluncount);
        likebtn = itemView.findViewById(R.id.feedbtnlike);
        commentbtn = itemView.findViewById(R.id.feedbtncomment);
        orwardbtn = itemView.findViewById(R.id.feedbtnforward);
        followbtn = itemView.findViewById(R.id.feedfollow);
        feedcontenttext = itemView.findViewById(R.id.feedcontent);
        feedcommenttext = itemView.findViewById(R.id.feedcomment);
        nicknametext = itemView.findViewById(R.id.feednickname);
        datetext = itemView.findViewById(R.id.feeddate);
        avatar = itemView.findViewById(R.id.feedavatar);
        feedcontentlayout = itemView.findViewById(R.id.feedcontentlayout);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public TextView getFeedlikecount() {
        return feedlikecount;
    }

    public void setFeedlikecount(TextView feedlikecount) {
        this.feedlikecount = feedlikecount;
    }

    public TextView getFeedpingluncount() {
        return feedpingluncount;
    }

    public void setFeedpingluncount(TextView feedpingluncount) {
        this.feedpingluncount = feedpingluncount;
    }

    public Button getLikebtn() {
        return likebtn;
    }

    public Button getCommentbtn() {
        return commentbtn;
    }

    public Button getOrwardbtn() {
        return orwardbtn;
    }

    public Button getFollowbtn() {
        return followbtn;
    }

    public TextView getFeedcontenttext() {
        return feedcontenttext;
    }

    public TextView getFeedcommenttext() {
        return feedcommenttext;
    }

    public TextView getNicknametext() {
        return nicknametext;
    }

    public TextView getDatetext() {
        return datetext;
    }

    public SimpleDraweeView getAvatar() {
        return avatar;
    }

    public TextView getBookname() {
        return bookname;
    }

    public void setBookname(TextView bookname) {
        this.bookname = bookname;
    }


    public LinearLayout getFeedcontentlayout() {
        return feedcontentlayout;
    }
}
