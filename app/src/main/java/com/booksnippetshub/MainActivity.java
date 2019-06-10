package com.booksnippetshub;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;

import com.booksnippetshub.fragment.CollectionFragment;
import com.booksnippetshub.fragment.DiscoveryFragment;
import com.booksnippetshub.fragment.MeFragment;
import com.booksnippetshub.fragment.NotificationFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //当前Fragment
    private Fragment currentFragment;
    private boolean isFirstStart = true;

    //初始化四个Fragment
    private DiscoveryFragment discoveryFragment = new DiscoveryFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MeFragment meFragment = new MeFragment();
    private CollectionFragment collectionFragment = new CollectionFragment();


    private void changeFragment(Fragment fragment) {

        if (currentFragment != fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments=fragmentManager.getFragments();
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
                    changeFragment(discoveryFragment);
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
    protected void onCreate(Bundle savedInstanceState) {


        Log.d("MainActivity:", "MainActivity onCreate");

//        if(isFirstStart==true){
        Fresco.initialize(this);
//        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (currentFragment == null) {
            changeFragment(discoveryFragment);
        }


        isFirstStart = false;

    }

}
