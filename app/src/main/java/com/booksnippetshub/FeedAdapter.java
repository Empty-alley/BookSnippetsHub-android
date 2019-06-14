package com.booksnippetshub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booksnippetshub.model.FeedModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    List<FeedModel> feedModels;

    public FeedAdapter(List<FeedModel> feedModels) {
        this.feedModels = feedModels;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedview, parent, false);
        FeedViewHolder holder = new FeedViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedModel feedModel = feedModels.get(position);
    }

    @Override
    public int getItemCount() {
        return feedModels.size();
    }
}
