package com.booksnippetshub.activity;

import android.os.Bundle;

import com.booksnippetshub.R;
import com.booksnippetshub.fragment.CollectMainFragment;
import com.booksnippetshub.fragment.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;

import com.booksnippetshub.fragment.CollectionFragment;
import com.booksnippetshub.fragment.MeFragment;
import com.booksnippetshub.fragment.NotificationFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //当前Fragment
    private Fragment currentFragment;
    private boolean isFirstStart = true;

    //初始化四个Fragment

//    private DiscoveryFragment mainFragment = new DiscoveryFragment();
    private MainFragment mainFragment = new MainFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MeFragment meFragment = new MeFragment();
    private CollectMainFragment collectionFragment = new CollectMainFragment();


    private void changeFragment(Fragment fragment) {

        if (currentFragment != fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (fragment.isAdded()) {
                transaction.hide(currentFragment);
                transaction.show(fragment);
                transaction.commit();
            } else {
                if (currentFragment != null) {
                    transaction.hide(currentFragment);
                }
                transaction.add(R.id.main_container, fragment);
                transaction.commit();
            }
            this.currentFragment = fragment;
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    setTitle(R.string.tabbar_discovery);
                    changeFragment(mainFragment);
                    return true;

                case R.id.navigation_collection:
                    setTitle(R.string.tabbar_collection);
                    changeFragment(collectionFragment);
                    return true;

                case R.id.navigation_notification:
                    setTitle(R.string.tabbar_notification);
                    changeFragment(notificationFragment);
                    return true;

                case R.id.navigation_me:
                    setTitle(R.string.tabbar_me);
                    changeFragment(meFragment);
                    return true;
            }
            return true;
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Log.d("MainActivity:", "MainActivity onCreate");

        Fresco.initialize(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (currentFragment == null) {
            changeFragment(mainFragment);
        }


        isFirstStart = false;

    }

}
