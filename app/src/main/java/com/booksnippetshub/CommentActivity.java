package com.booksnippetshub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.model.Comment;

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


    private int feedid;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        feedid = getIntent().getIntExtra("feedid", 0);


        setfeed(feedid);
        getcomment(feedid);



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

                runOnUiThread(() -> {
                    //TODO: set text


                });
            }
        });
    }

    private void getcomment(int feedid) {


        List<Comment> comments = new ArrayList<>();

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

                for (int i = 0; i < array.size(); i++) {

                    JSONObject j=array.getJSONObject(i);
                    Comment temp = j.toJavaObject(Comment.class);
                    comments.add(temp);
                }


                int a=123;

            }
        });
    }


}
