package com.booksnippetshub.model;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBackgroundmageurl() {
        return backgroundmageurl;
    }

    public void setBackgroundmageurl(String backgroundmageurl) {
        this.backgroundmageurl = backgroundmageurl;
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent;
    }

    public String getBookcomment() {
        return bookcomment;
    }

    public void setBookcomment(String bookcomment) {
        this.bookcomment = bookcomment;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isFolded() {
        return isFolded;
    }

    public void setFolded(boolean folded) {
        isFolded = folded;
    }

    public boolean isIsfollow() {
        return isfollow;
    }

    public void setIsfollow(boolean isfollow) {
        this.isfollow = isfollow;
    }

    public boolean isIsforward() {
        return isforward;
    }

    public void setIsforward(boolean isforward) {
        this.isforward = isforward;
    }

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

    public FeedModel() {
    }

    public FeedModel(int id, int userid, String nickname, String time, String avatarUrl, String backgroundmageurl, String bookcontent, String bookcomment, int bookid, String bookname, String from, boolean isFolded, boolean isfollow, boolean isforward, boolean isliked, int likecount, int commentcount) {
        this.id = id;
        this.userid = userid;
        this.nickname = nickname;
        this.time = time;
        this.avatarUrl = avatarUrl;
        this.backgroundmageurl = backgroundmageurl;
        this.bookcontent = bookcontent;
        this.bookcomment = bookcomment;
        this.bookid = bookid;
        this.bookname = bookname;
        this.from = from;
        this.isFolded = isFolded;
        this.isfollow = isfollow;
        this.isforward = isforward;
        this.isliked = isliked;
        this.likecount = likecount;
        this.commentcount = commentcount;
    }
    public FeedModel(int id, int userid, String nickname, String time, String avatarUrl, String backgroundmageurl, String bookcontent, String bookcomment, int bookid, String bookname, String from,  boolean isfollow, boolean isforward, boolean isliked, int likecount, int commentcount) {
        this.id = id;
        this.userid = userid;
        this.nickname = nickname;
        this.time = time;
        this.avatarUrl = avatarUrl;
        this.backgroundmageurl = backgroundmageurl;
        this.bookcontent = bookcontent;
        this.bookcomment = bookcomment;
        this.bookid = bookid;
        this.bookname = bookname;
        this.from = from;
        this.isfollow = isfollow;
        this.isforward = isforward;
        this.isliked = isliked;
        this.likecount = likecount;
        this.commentcount = commentcount;
    }
}
