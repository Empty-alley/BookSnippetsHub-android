package com.booksnippetshub.activity;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    Button loginbtn;
    Button toregisterbtn;
    EditText nicknameinput;
    EditText passwordinput;


    Intent toMain;

    AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("登录");
        String nickSaved = CONFIG.accountSharedPreferences.getString("nickname", "");

        toMain = new Intent(this, MainActivity.class);

        loginbtn = findViewById(R.id.loginbtn);
        toregisterbtn = findViewById(R.id.toregisterbtn);
        nicknameinput = findViewById(R.id.nicknameinput);
        passwordinput = findViewById(R.id.passwordinput);
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("提示");

        nicknameinput.setText(nickSaved);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = nicknameinput.getText().toString();
                String password = passwordinput.getText().toString();

                if (nickname.length() == 0 || password.length() == 0) {
                    alertDialogBuilder.setMessage("用户名或密码不能为空");
                    alertDialogBuilder.setPositiveButton("确定", null);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
//                    sharedPreferences.edit().putString("nickname", nickname).apply();
                    login(nickname, password);

                }

            }
        });


        Intent toregister = new Intent(this, RegisterActivity.class);

        toregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toregister);
            }
        });

    }


    private void login(String nickname, String password) {

        JSONObject requestjson = new JSONObject();
        requestjson.put("nickname", nickname);
        requestjson.put("password", password);

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestjson.toJSONString());

        Request request = new Request.Builder().url(CONFIG.baseUrl + "/restlogin").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsestring = response.body().string();
                Log.d("response string", responsestring);

                JSONObject responejson = JSONObject.parseObject(responsestring);
                if (responejson.getInteger("errcode") == 0) {

                    CONFIG.accountSharedPreferences.edit().putString("token", responejson.getString("token")).apply();
                    CONFIG.token = responejson.getString("token");

                    Log.d("token", responejson.getString("token"));
                    CONFIG.accountSharedPreferences.edit().putString("nickname", nickname).apply();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(toMain);
                        }
                    });
                } else if (responejson.getInteger("errcode") == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String errmsg = responejson.getString("errmsg");
                            alertDialogBuilder.setMessage(errmsg);
                            alertDialogBuilder.setPositiveButton("确定", null);
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    });
                }
            }
        });
    }
}
