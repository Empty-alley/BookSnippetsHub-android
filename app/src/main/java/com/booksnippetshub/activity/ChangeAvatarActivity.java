package com.booksnippetshub.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;
import com.booksnippetshub.activity.MainActivity;
import com.booksnippetshub.utils.UriToByteArray;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChangeAvatarActivity extends AppCompatActivity {

    private static final int CHANGE_AVATAR = 5;

    Button changeAvatarbtn;
    Button jumpover;

    SimpleDraweeView avatarDraweeView;

    Intent toMain;


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void toChangeAvatarActivity() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, this.CHANGE_AVATAR);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("选择头像");
        setContentView(R.layout.activity_change_avatar);


        changeAvatarbtn = findViewById(R.id.changeavatar);
        jumpover = findViewById(R.id.jumpover);
        avatarDraweeView = findViewById(R.id.avatarDraweeView);
        avatarDraweeView.setImageURI(Uri.parse(CONFIG.baseUrl + "/sysimg/defaultavatar.png"));

        toMain = new Intent(this, MainActivity.class);

        changeAvatarbtn.setOnClickListener((View v) -> {
            toChangeAvatarActivity();
        });

        jumpover.setOnClickListener((View v) -> {
            startActivity(toMain);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        if (requestCode == this.CHANGE_AVATAR) {
            RequestBody image = RequestBody.create(null, UriToByteArray.to(data.getData(), this));

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", "avatarimg.jpg", image)
                    .build();

            Request request = new Request.Builder().post(requestBody).url(CONFIG.baseUrl + "/setavatar").build();

            Context thiscontext = this;
            OkHttpClient ac = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();
            ac.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responsestring = response.body().string();
                    JSONObject jsonObject = JSONObject.parseObject(responsestring);
                    if (jsonObject.getInteger("errcode") == 0) {
                        runOnUiThread(() -> {
                            avatarDraweeView.setImageURI(Uri.parse(jsonObject.getString("url")));
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(() -> {
                            Toast.makeText(thiscontext, "修改成功", Toast.LENGTH_SHORT).show();
                            startActivity(toMain);
                        });
                    }
                }
            });
        }
    }

}
