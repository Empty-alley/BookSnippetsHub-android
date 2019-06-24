package com.booksnippetshub;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

public class NotifationViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView  avatar;
    LinearLayout linearLayout;

    TextView  nickname;
    TextView  commit;


    public NotifationViewHolder(@NonNull View itemView) {
        super(itemView);

        avatar = itemView.findViewById(R.id.avatarview);
        nickname = itemView.findViewById(R.id.nickName);
        commit = itemView.findViewById(R.id.commit);

    }

    public SimpleDraweeView getAvatar() {
        return avatar;
    }

    public void setAvatar(SimpleDraweeView avatar) {
        this.avatar = avatar;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public TextView getNickname() {
        return nickname;
    }

    public void setNickname(TextView nickname) {
        this.nickname = nickname;
    }

    public TextView getCommit() {
        return commit;
    }

    public void setCommit(TextView commit) {
        this.commit = commit;
    }
}
