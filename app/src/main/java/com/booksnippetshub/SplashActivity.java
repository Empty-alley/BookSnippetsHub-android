package com.booksnippetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (sharedPreferences.getString("token", "").length() != 0) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            } else {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
            }
        }).start();
    }


}
