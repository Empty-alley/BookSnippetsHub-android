package com.booksnippetshub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.model.Comment;
import com.booksnippetshub.model.FeedModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

    List<Comment> commentss = new ArrayList<>();
    RecyclerView commentlist;

    private int feedid;
    private int id;
    private int userid;
    private boolean isfollow;
    CommentAdapter commentAdapter;
    boolean isliked;

    int likecount;
    int commentcount;
    Button likebtn;
    Button commentbtn;
    Button orwardbtn;
    Button followbtn;
    Button commentbutton;
    TextView commenteditText;


    TextView feedcontenttext;
    TextView feedcommenttext;
    TextView nicknametext;
    TextView datetext;

    TextView feedlikecount;
    TextView feedpingluncount;

    SimpleDraweeView avatar;

    LinearLayout feedcontentlayout;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        feedid = getIntent().getIntExtra("feedid", 0);

        commentlist = findViewById(R.id.historycomment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentlist.setLayoutManager(linearLayoutManager);


        feedlikecount = findViewById(R.id.feedlikecount);
        feedpingluncount = findViewById(R.id.feedpingluncount);
        likebtn = findViewById(R.id.feedbtnlike);
        commentbtn = findViewById(R.id.feedbtncomment);
        orwardbtn = findViewById(R.id.feedbtnforward);
        followbtn = findViewById(R.id.feedfollow);
        feedcontenttext = findViewById(R.id.feedcontent);
        feedcommenttext = findViewById(R.id.feedcomment);
        nicknametext = findViewById(R.id.feednickname);
        datetext = findViewById(R.id.feeddate);
        avatar = findViewById(R.id.feedavatar);
        feedcontentlayout = findViewById(R.id.feedcontentlayout);

        commentbutton = findViewById(R.id.commentbutton);
        commenteditText = findViewById(R.id.commenteditText);

        setfeed(feedid);
        getcomment(feedid);


        commentbutton.setOnClickListener((View v) -> {
            String commenteditstring = commenteditText.getText().toString();

            JSONObject requestjson = new JSONObject();
            requestjson.put("feedid", feedid);
            requestjson.put("commentvalue", commenteditstring);

            Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), requestjson.toJSONString())).url(CONFIG.baseUrl + "/addcomment").build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responestring = response.body().string();

                    JSONObject responsejson = JSONObject.parseObject(responestring);

                    if (responsejson.getBoolean("isok") == true) {
                        responsejson = responsejson.getJSONObject("comment");

                        String avatarurl = responsejson.getString("AvatarUrl");
                        if (avatarurl.startsWith("/")) avatarurl = CONFIG.baseUrl + avatarurl;
                        String nickname = responsejson.getString("nickname");
                        String comment = responsejson.getString("comment");

                        String finalAvatarurl = avatarurl;
                        runOnUiThread(() -> {
                            commentAdapter.addComment(new Comment(finalAvatarurl, comment, nickname));
                            commentAdapter.notifyDataSetChanged();
                            Toast.makeText(CommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        });
                    }
                }
            });

        });


    }


    private void setfeed(int feedid) {

        JSONObject requestjson = new JSONObject();
        requestjson.put("feedid", feedid);

        Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), requestjson.toJSONString())).url(CONFIG.baseUrl + "/getfeed").build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responestring = response.body().string();
                JSONArray array = JSONArray.parseArray(responestring);

                JSONObject responejson = array.getJSONObject(0);

                int a = 234;
                runOnUiThread(() -> {
                    feedlikecount.setText(responejson.getString("likecount"));
                    feedcontenttext.setText(responejson.getString("bookcontent"));
                    feedcommenttext.setText(responejson.getString("bookcomment"));
                    nicknametext.setText(responejson.getString("nickname"));
                    datetext.setText(responejson.getString("time"));
                    feedpingluncount.setText(responejson.getString("commentcount"));
                    avatar.setImageURI(Uri.parse(responejson.getString("avatarUrl")));

                    if (responejson.getBoolean("isfollow")) {
                        followbtn.setText("未关注");
                    } else {
                        followbtn.setText("关注");
                    }
                    if (responejson.getBoolean("isFolded")) {


                    } else {

                    }
                    if (responejson.getBoolean("isliked")) {
                        likebtn.setBackgroundResource(R.drawable.likefill);
                    } else {
                        likebtn.setBackgroundResource(R.drawable.like);
                    }

                });
            }
        });
    }

    private void getcomment(int feedid) {


        List<Comment> comments = new ArrayList<>();

        JSONObject requestjson = new JSONObject();
        requestjson.put("feedid", feedid);
        Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), requestjson.toJSONString())).url(CONFIG.baseUrl + "/getallcomment").build();

        Context thiscontext = this;

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responestring = response.body().string();
                JSONArray array = JSONArray.parseArray(responestring);

                for (int i = 0; i < array.size(); i++) {

                    JSONObject j = array.getJSONObject(i);
                    Comment temp = j.toJavaObject(Comment.class);
                    comments.add(temp);
                }

                commentAdapter = new CommentAdapter(comments);

                runOnUiThread(() -> {
                    commentAdapter.setContext(thiscontext);
                    commentlist.setAdapter(commentAdapter);
                });


            }
        });
    }


}
