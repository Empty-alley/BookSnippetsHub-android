package com.booksnippetshub.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.*;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;
import com.booksnippetshub.fragment.DiscoveryFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RealseActivity extends AppCompatActivity {

    EditText bookname;
    EditText bookcontent;
    EditText bookidea;
    RecyclerView booktheme;
    Button bookreleasebtn;

    AlertDialog.Builder alertDialogBuilder;


    JSONArray bgimglist = new JSONArray();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getalltheme();

        setTitle("发布");
        setContentView(R.layout.activity_release);
        bookname = (EditText) findViewById(R.id.bookname);
        bookcontent = (EditText) findViewById(R.id.bookcontent);
        bookidea = (EditText) findViewById(R.id.bookidea);
        booktheme = (RecyclerView) findViewById(R.id.booktheme);
        bookreleasebtn = (Button) findViewById(R.id.bookrelease);

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("提示");
        alertDialogBuilder.setPositiveButton("确定", null);




        bookreleasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String releasebookname = bookname.getText().toString();
                String releasebookcontent = bookcontent.getText().toString();
                String releasebookidea = bookidea.getText().toString();

                int bgimgid  = 1;

                if (releasebookname.equals("")) {
                    alertDialogBuilder.setMessage("书名不能为空");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else if (!releasebookname.equals("")&&releasebookcontent.equals("")) {
                    alertDialogBuilder.setMessage("摘抄内容不能为空");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {

                    release(releasebookname, releasebookcontent, releasebookidea,bgimgid);
                }
            }
        });




    }

    private void release(String releasebookname, String releasebookcontent, String releasebookidea,int bgimgid){
        JSONObject requestjson = new JSONObject();
        requestjson.put("bookname",releasebookname);
        requestjson.put("bookcontent",releasebookcontent);
        requestjson.put("bookcomment",releasebookidea);
        requestjson.put("bgimgid",bgimgid);


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestjson.toJSONString());

        Request request = new Request.Builder().url(CONFIG.baseUrl + "/addfeed").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                int a = 123;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject responejson = JSONObject.parseObject(response.body().string());
                if (responejson.getInteger("errcode") == 0) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            alertDialogBuilder.setMessage("发布成功");
                            alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();

                                        }
                                    });
                                }
                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    });

                } else if (responejson.getInteger("errcode") == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder errmsgs = new StringBuilder();
                            for (Object errmsg : responejson.getJSONArray("errmsg")) {
                                errmsgs.append(errmsg);
                            }

                            alertDialogBuilder.setMessage(errmsgs.toString());
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    });
                }
            }
        });

    }

    private void getalltheme(){

        OkHttpClient okHttpClient = new OkHttpClient();
    //    Request request = new Request.Builder().url("http://api.booksnippetshub.com/getallbackgroundimage").build();
        Request request = new Request.Builder().url(CONFIG.baseUrl + "/getallbackgroundimage").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                int a = 123;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject responejson = JSONObject.parseObject(response.body().string());
                if (responejson.getInteger("errcode") == 0) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bgimglist = responejson.getJSONArray("backgroundImages");
                        }
                    });

                } else if (responejson.getInteger("errcode") == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder errmsgs = new StringBuilder();
                            for (Object errmsg : responejson.getJSONArray("errmsg")) {
                                errmsgs.append(errmsg);
                            }

                            alertDialogBuilder.setMessage(errmsgs.toString());
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    });
                }
            }
        });

    }
}
