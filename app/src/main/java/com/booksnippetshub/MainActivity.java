package com.booksnippetshub;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {



    private DiscoveryFragment discoveryFragment = new DiscoveryFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MeFragment meFragment = new MeFragment();
    private CollectionFragment collectionFragment = new CollectionFragment();


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    setTitle(R.string.tabbar_discovery);
                    replaceFragment(discoveryFragment);
                    return true;

                case R.id.navigation_collection:
                    setTitle(R.string.tabbar_collection);
                    replaceFragment(collectionFragment);
                    return true;

                case R.id.navigation_notification:
                    setTitle(R.string.tabbar_notification);
                    replaceFragment(notificationFragment);
                    return true;

                case R.id.navigation_me:
                    setTitle(R.string.tabbar_me);
                    replaceFragment(meFragment);
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
