package com.example.ygh.ttbd.activityMain.home.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ygh on 2016/12/21.
 */

public class HomeTabFragmentPagerAdapter extends FragmentPagerAdapter
{
    List<Fragment> mFragments;

    public HomeTabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        Bundle arguments = mFragments.get(position).getArguments();
        return arguments.getString("title");
    }

}
