package com.booksnippetshub;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    Button likebtn;
    Button commentbtn;
    Button orwardbtn;
    Button followbtn;

    TextView feedcontenttext;
    TextView feedcommenttext;
    TextView nicknametext;
    TextView datetext;

    SimpleDraweeView avatar;

    LinearLayout feedcontentlayout;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        likebtn = itemView.findViewById(R.id.feedbtnlike);
        commentbtn = itemView.findViewById(R.id.feedbtncomment);
        orwardbtn = itemView.findViewById(R.id.feedbtnforward);
        followbtn = itemView.findViewById(R.id.feedfollow);
        feedcontenttext = itemView.findViewById(R.id.feedcontent);
        feedcommenttext = itemView.findViewById(R.id.feedcomment);
        nicknametext = itemView.findViewById(R.id.nickName);
        datetext = itemView.findViewById(R.id.feeddate);
        avatar = itemView.findViewById(R.id.feedavatar);
        feedcontentlayout = itemView.findViewById(R.id.feedcontentlayout);
    }


}
