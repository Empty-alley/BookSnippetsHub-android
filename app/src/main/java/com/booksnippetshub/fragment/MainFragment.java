package com.booksnippetshub.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.booksnippetshub.MainFragmentPagerAdapter;
import com.booksnippetshub.R;


public class MainFragment extends Fragment {

    ViewPager viewPager;
    TextView followbtn;
    TextView recommand;

    LinearLayout recommandbottomline;
    LinearLayout followbottomline;


    public MainFragment() {
        this.setArguments(new Bundle());
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainpage, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = getActivity().findViewById(R.id.viewpager);

        followbtn = getActivity().findViewById(R.id.follow);
        recommand = getActivity().findViewById(R.id.recommand);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getActivity().getSupportFragmentManager());

        recommandbottomline = getActivity().findViewById(R.id.recommandbottomline);
        followbottomline = getActivity().findViewById(R.id.followbottomline);


        mainFragmentPagerAdapter.addFragment(new DiscoveryFragment());
        mainFragmentPagerAdapter.addFragment(new FollowFeedFragment());


        viewPager.setAdapter(mainFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        showRecommandBottomLine();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    showFollowBottomLine();
                } else if (position == 0) {
                    showRecommandBottomLine();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        recommand.setOnClickListener((View v) -> {
            if (viewPager.getCurrentItem() != 0) {
                viewPager.setCurrentItem(0, true);
                showRecommandBottomLine();
            }

        });
        followbtn.setOnClickListener((View v) -> {
            if (viewPager.getCurrentItem() != 1) {
                viewPager.setCurrentItem(1, true);
                showFollowBottomLine();
            }
        });
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void showRecommandBottomLine() {
        recommandbottomline.setVisibility(View.VISIBLE);
        followbottomline.setVisibility(View.GONE);
    }

    private void showFollowBottomLine() {
        recommandbottomline.setVisibility(View.GONE);
        followbottomline.setVisibility(View.VISIBLE);
    }

}
