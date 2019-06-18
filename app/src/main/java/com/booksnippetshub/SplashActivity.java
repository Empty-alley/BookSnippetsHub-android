package com.booksnippetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.telecom.Conference;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CONFIG.accountSharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        CONFIG.settingSharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);

        CONFIG.token = CONFIG.accountSharedPreferences.getString("token", "");

    }

    private void toLoginOrMain() {
        if (CONFIG.accountSharedPreferences.getString("token", "").length() != 0) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean showsplash = CONFIG.settingSharedPreferences.getBoolean("showsplash", true);
        if (showsplash == false) {
            toLoginOrMain();
        } else {
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

}
