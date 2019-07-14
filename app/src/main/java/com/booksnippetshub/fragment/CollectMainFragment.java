package com.booksnippetshub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.booksnippetshub.MainFragmentPagerAdapter;
import com.booksnippetshub.R;


public class CollectMainFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout followbtn;
    TextView bookstorebtn;

    LinearLayout followbookbottomline;
    LinearLayout bookstorebottomline;


    public CollectMainFragment() {
        this.setArguments(new Bundle());
    }

    public static CollectMainFragment newInstance(String param1, String param2) {
        CollectMainFragment fragment = new CollectMainFragment();
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
        return inflater.inflate(R.layout.fragment_collection, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = getActivity().findViewById(R.id.viewpager);

        followbtn = getActivity().findViewById(R.id.follow);
        bookstorebtn = getActivity().findViewById(R.id.bookstore);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getChildFragmentManager());

        followbookbottomline = getActivity().findViewById(R.id.followbookbottomline);
        bookstorebottomline = getActivity().findViewById(R.id.bookstorebottomline);


        mainFragmentPagerAdapter.addFragment(new DiscoveryFragment());
        mainFragmentPagerAdapter.addFragment(new CollectionFragment());


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

        bookstorebtn.setOnClickListener((View v) -> {
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
        followbookbottomline.setVisibility(View.GONE);
        bookstorebottomline.setVisibility(View.GONE);
    }

    private void showFollowBottomLine() {
        followbookbottomline.setVisibility(View.GONE);
        bookstorebottomline.setVisibility(View.GONE);
    }

}
