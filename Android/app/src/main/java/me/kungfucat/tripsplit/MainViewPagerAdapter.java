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
    int userId;

    public MainViewPagerAdapter(FragmentManager fm, int userId) {
        super(fm);
        arrayList.add("Groups");
        arrayList.add("Friends");
        arrayList.add("History");
        this.userId = userId;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            GroupFragment groupFragment = GroupFragment.newInstance();
            fragment = groupFragment;
        } else if (position == 1) {
            FriendFragment friendFragment = FriendFragment.newInstance();
            fragment = friendFragment;
        } else if (position == 2) {
            HistoryFragment historyFragment = HistoryFragment.newInstance(userId);
            fragment = historyFragment;
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