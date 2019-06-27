package com.booksnippetshub;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    List<Fragment> fragments=new ArrayList<>();

    public void  addFragment(Fragment f){
        fragments.add(f);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
