package com.booksnippetshub.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;

import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CONFIG.accountSharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        CONFIG.settingSharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);

        CONFIG.token = CONFIG.accountSharedPreferences.getString("token", "");

        getSupportActionBar().hide();
        boolean showsplash = CONFIG.settingSharedPreferences.getBoolean("showsplash", true);
        if (showsplash == false) {
            toLoginOrMain();
        } else {

            setContentView(R.layout.activity_splash);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toLoginOrMain();
                            }
                        });
                    }
                }
            }).start();
        }

    }

    private void toLoginOrMain() {
        if (CONFIG.accountSharedPreferences.getString("token", "").length() != 0) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }



}
