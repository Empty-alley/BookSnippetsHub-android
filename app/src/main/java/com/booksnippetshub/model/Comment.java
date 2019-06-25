package com.booksnippetshub.model;

public class Comment {
    String AvatarUrl;
    String comment;
    String nickname;


    public Comment() {
    }

    public Comment(String avatarUrl, String comment, String nickname) {
        AvatarUrl = avatarUrl;
        this.comment = comment;
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        AvatarUrl = avatarUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
