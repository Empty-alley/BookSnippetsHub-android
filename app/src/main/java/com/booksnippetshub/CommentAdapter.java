package com.booksnippetshub;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booksnippetshub.model.Comment;
import com.booksnippetshub.model.NotifationModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    List<Comment> comments;


    public void addComment(Comment c) {
        comments.add(c);
    }

    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentview, parent, false);
        CommentViewHolder holder = new CommentViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);

        holder.getNickname().setText(comment.getNickname());
        holder.getCommit().setText(comment.getComment());
        holder.getAvatar().setImageURI(Uri.parse(comment.getAvatarUrl()));


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
