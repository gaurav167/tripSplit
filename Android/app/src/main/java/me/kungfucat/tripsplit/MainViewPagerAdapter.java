package me.kungfucat.tripsplit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by harsh on 2/13/18.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> arrayList = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        arrayList.add("Friends");
        arrayList.add("Groups");
        arrayList.add("History");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            GroupFragment groupFragment = GroupFragment.newInstance();
            fragment = groupFragment;
        } else if (position == 1) {
            GroupFragment groupFragment = GroupFragment.newInstance();
            fragment = groupFragment;
        } else if (position == 2) {
            GroupFragment groupFragment = GroupFragment.newInstance();
            fragment = groupFragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}