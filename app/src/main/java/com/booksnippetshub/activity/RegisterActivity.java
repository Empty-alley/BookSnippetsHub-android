package com.booksnippetshub.activity;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    Button registerbtn;
    EditText nicknamereginput;
    EditText passwordreginput;
    EditText repeatpasswordreginput;


    Intent toChangeAvatarActivity;

    AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("注册");

        toChangeAvatarActivity = new Intent(this, ChangeAvatarActivity.class);

        registerbtn = findViewById(R.id.registerbtn);
        nicknamereginput = findViewById(R.id.nicknamereginput);
        passwordreginput = findViewById(R.id.passwordreginput);
        repeatpasswordreginput = findViewById(R.id.repeatpasswordreginput);

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("提示");
        alertDialogBuilder.setPositiveButton("确定", null);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = nicknamereginput.getText().toString();
                String password = passwordreginput.getText().toString();
                String repeatpassword = repeatpasswordreginput.getText().toString();

                if (!password.equals(repeatpassword)) {
                    alertDialogBuilder.setMessage("密码不一致");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else if (nickname.length() == 0 || password.length() == 0 || repeatpassword.length() == 0) {
                    alertDialogBuilder.setMessage("用户名或密码不能为空");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    register(nickname, password, repeatpassword);
                }
            }
        });
    }

    private void register(String nickname, String password, String repeatpassword) {
        JSONObject requestjson = new JSONObject();
        requestjson.put("nickname", nickname);
        requestjson.put("password", password);
        requestjson.put("repeatpassword", repeatpassword);

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestjson.toJSONString());

        Request request = new Request.Builder().url(CONFIG.baseUrl + "/signup").post(requestBody).build();
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
                            String token = responejson.getString("token");
                            CONFIG.token = token;

                            CONFIG.accountSharedPreferences.edit().putString("token", token).apply();
                            CONFIG.accountSharedPreferences.edit().putString("nickname", nickname).apply();

                            alertDialogBuilder.setMessage("注册成功");
                            alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(toChangeAvatarActivity);
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
}
