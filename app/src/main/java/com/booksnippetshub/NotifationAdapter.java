package com.booksnippetshub;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booksnippetshub.model.NotifationModel;

import java.util.List;

public class NotifationAdapter extends RecyclerView.Adapter<NotifationViewHolder> {
    List<NotifationModel> NotifationModels;

    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NotifationAdapter(List<NotifationModel> NotifationModels) {
        this.NotifationModels = NotifationModels;
    }



    @NonNull
    @Override
    public NotifationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticationview, parent, false);
        NotifationViewHolder holder = new NotifationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifationViewHolder holder, int position) {
        NotifationModel notifationModel = NotifationModels.get(position);

        holder.getNickname().setText(notifationModel.getFromnickname());
        holder.getCommit().setText(notifationModel.getMsg());
        holder.getAvatar().setImageURI(Uri.parse(CONFIG.baseUrl+notifationModel.getFromavatarurl()));


    }

    @Override
    public int getItemCount() {
        return NotifationModels.size();
    }
}
