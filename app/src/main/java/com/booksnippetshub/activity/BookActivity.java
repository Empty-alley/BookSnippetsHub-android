package com.booksnippetshub.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;
import com.booksnippetshub.model.FeedModel;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookActivity extends AppCompatActivity {


    int thisbookid;

    TextView bookname;
    Button star;
    TextView description;
    SimpleDraweeView avatarDraweeView;
    boolean islike;
    JSONObject bookidrequestbody;


    OkHttpClient okHttpClientWithoutAuthorization = new OkHttpClient();

    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        bookname = findViewById(R.id.bookname);
        star = findViewById(R.id.star);
        avatarDraweeView = findViewById(R.id.avatarDraweeView);
        description = findViewById(R.id.description);
        bookname.setText(getIntent().getStringExtra("bookname"));

        int bookid = getIntent().getIntExtra("bookid", 0);


        bookidrequestbody = new JSONObject();
        bookidrequestbody.put("bookid", bookid);

        Request bookinforequest = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), bookidrequestbody.toJSONString())).url(CONFIG.baseUrl + "/getbook").build();
        okHttpClientWithoutAuthorization.newCall(bookinforequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject responsejson = JSONObject.parseObject(response.body().string());
                thisbookid = responsejson.getInteger("id");

                runOnUiThread(() -> {
                    description.setText(responsejson.getString("description"));
                    avatarDraweeView.setImageURI(Uri.parse(CONFIG.baseUrl + responsejson.getString("bookcoverimgurl")));

                });


            }
        });

        Request islikerequest = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), bookidrequestbody.toJSONString())).url(CONFIG.baseUrl + "/islike").build();
        okHttpClient.newCall(islikerequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                JSONObject responsejson = JSONObject.parseObject(response.body().string());

                islike = responsejson.getBoolean("islike");

                runOnUiThread(() -> {

                    if (islike) {
                        star.setBackgroundResource(R.drawable.likefill);
                    }
                });
            }
        });

        star.setOnClickListener((View v) -> {


            Request likebookrequest = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), bookidrequestbody.toJSONString())).url(CONFIG.baseUrl + "/likebook").build();
            Request dislikebookrequest = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), bookidrequestbody.toJSONString())).url(CONFIG.baseUrl + "/dislikebook").build();

            Request temp;

            if (islike) {
                temp = dislikebookrequest;
                star.setBackgroundResource(R.drawable.star);
            } else {
                temp = likebookrequest;
                star.setBackgroundResource(R.drawable.starfill);
            }
            islike = !islike;
            okHttpClient.newCall(temp).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    JSONObject responsejson = JSONObject.parseObject(response.body().string());
                    boolean isok = responsejson.getBoolean("isok");

                }
            });


        });
    }

}
