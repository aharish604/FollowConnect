package com.appcare.followconnect.Home.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.appcare.followconnect.R;
import com.appcare.followconnect.Home.fragments.ChatFragment;
import com.appcare.followconnect.Home.fragments.IntotoFragment;
import com.appcare.followconnect.Home.fragments.MyviewFragment;
import com.appcare.followconnect.Home.fragments.SpooLvidFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3,R.string.tab_text_4 };
    private final Context mContext;
    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return  MyviewFragment.newInstance("","");
            case 1:
                return  IntotoFragment.newInstance("","");
            case 2:
                return  SpooLvidFragment.newInstance("","");
            case 3:
                return ChatFragment.newInstance("","");
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        // Show 3 total pages.
        return TAB_TITLES.length;
    }
}
