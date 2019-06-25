package com.booksnippetshub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.model.FeedModel;
import com.facebook.drawee.view.SimpleDraweeView;


import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    List<FeedModel> feedModels;

    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();

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

        holder.setContext(getContext());

        holder.getNicknametext().setText(feedModel.getNickname());
        holder.getDatetext().setText(feedModel.getTime());
        holder.getAvatar().setImageURI(Uri.parse(feedModel.getAvatarUrl()));
        holder.getFeedcontenttext().setText(feedModel.getBookcontent());
        holder.getFeedcommenttext().setText(feedModel.getBookcomment());

        holder.setId(feedModel.getId());
        holder.setUserid(feedModel.getUserid());
        holder.setIsliked(feedModel.isIsliked());

        holder.getFeedlikecount().setText(String.valueOf(feedModel.getLikecount()));
        holder.getFeedpingluncount().setText(String.valueOf(feedModel.getCommentcount()));

        holder.getFeedcontentlayout().setOnClickListener((View v) -> {

            Log.d("log", "Feedcontentlayout");
        });

        if (feedModel.isIsliked()) {
            holder.getLikebtn().setText("Y");
            holder.getLikebtn().setBackgroundResource(R.drawable.likefill);
        } else {
            holder.getLikebtn().setText("N");
            holder.getLikebtn().setBackgroundResource(R.drawable.like);
        }

        holder.getLikebtn().setOnClickListener((View v) -> {

            JSONObject likedislikerequestjson = new JSONObject();
            likedislikerequestjson.put("feedid", holder.getId());

            if (holder.isIsliked()) {
                Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), likedislikerequestjson.toJSONString())).url(CONFIG.baseUrl + "/dislikefeed").build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responestring = response.body().string();

                        JSONObject responsejson = JSONObject.parseObject(responestring);
                        if (responsejson.getBoolean("isok") == true) {
                            ((Activity) holder.getContext()).runOnUiThread(() -> {
                                        holder.getLikebtn().setBackgroundResource(R.drawable.like);


                                    }
                            );
                        }
                    }
                });
                holder.getLikebtn().setText("N");
                holder.getFeedlikecount().setText(String.valueOf(Integer.valueOf(holder.getFeedlikecount().getText().toString()) - 1));
            } else {
                Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), likedislikerequestjson.toJSONString())).url(CONFIG.baseUrl + "/likefeed").build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responestring = response.body().string();
                        JSONObject responsejson = JSONObject.parseObject(responestring);
                        if (responsejson.getBoolean("isok") == true) {
                            ((Activity) holder.getContext()).runOnUiThread(() -> {
                                        holder.getLikebtn().setBackgroundResource(R.drawable.likefill);

                                    }
                            );
                        }
                    }
                });
                holder.getLikebtn().setText("Y");
                holder.getFeedlikecount().setText(String.valueOf(Integer.valueOf(holder.getFeedlikecount().getText().toString()) + 1));
            }
            holder.setIsliked(!holder.isIsliked());
        });

        holder.getCommentbtn().setOnClickListener((View v) -> {
            Intent toCommentActivity=new Intent(getContext(),CommentActivity.class);
            toCommentActivity.putExtra("feedid",holder.getId());
            getContext().startActivity(toCommentActivity);

        });


        if (feedModel.isIsforward()) {
            holder.getFollowbtn().setText("Y");
        } else {
            holder.getFollowbtn().setText("N");
        }


        JSONObject followordisfollow = new JSONObject();
        followordisfollow.put("userid", holder.getUserid());
        holder.setIsfollow(feedModel.isIsfollow());

        if (feedModel.isIsfollow()) {
            holder.getFollowbtn().setText("已关注");
        } else {
            holder.getFollowbtn().setText("关注");
        }


        holder.getFollowbtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isIsfollow()) {
                    Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), followordisfollow.toJSONString())).url(CONFIG.baseUrl + "/disfollow").build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responestring = response.body().string();
                            ((Activity) holder.getContext()).runOnUiThread(() -> {

                                        JSONObject responsejson = JSONObject.parseObject(responestring);
                                        if (responsejson.getBoolean("isok") == true) {
                                            holder.getFollowbtn().setText("关注");
                                            holder.setIsfollow(false);
                                        }
                                    }
                            );
                        }
                    });

                } else {

                    Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), followordisfollow.toJSONString())).url(CONFIG.baseUrl + "/follow").build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responestring = response.body().string();
                            ((Activity) holder.getContext()).runOnUiThread(() -> {
                                        JSONObject responsejson = JSONObject.parseObject(responestring);
                                        if (responsejson.getBoolean("isok") == true) {
                                            holder.getFollowbtn().setText("已关注");
                                            holder.setIsfollow(true);
                                        }

                                    }
                            );

                        }
                    });

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return feedModels.size();
    }
}
