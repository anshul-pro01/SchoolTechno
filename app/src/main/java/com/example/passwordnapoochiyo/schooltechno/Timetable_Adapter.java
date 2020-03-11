package com.example.passwordnapoochiyo.schooltechno;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

class Timetable_Adapter extends FragmentStatePagerAdapter {
    private Activity myContext;
    int totalTabs;

    public Timetable_Adapter(FragmentManager fm, int totalTabs) {
        super(fm);

        this.totalTabs = totalTabs;

    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                MonFragment mon_fragment = new MonFragment();
            Bundle bundle1=new Bundle();
            mon_fragment.setArguments(bundle1);
            return mon_fragment;
            case 1:
             //   TueFragment tue_fragment = new TueFragment();
             //   return tue_fragment;
                TueFragment tue_fragment = new TueFragment();
                Bundle bundle2=new Bundle();
                tue_fragment.setArguments(bundle2);
                return tue_fragment;

            case 2:
               // WedFragment wed_fragment = new WedFragment();
               // return wed_fragment;
                WedFragment wed_fragment = new WedFragment();
                Bundle bundle3=new Bundle();
                wed_fragment.setArguments(bundle3);
                return wed_fragment;
            case 3:
               // ThuFragment thu_fragment = new ThuFragment();
               // return thu_fragment;
                ThuFragment thu_fragment = new ThuFragment();
                Bundle bundle4=new Bundle();
                thu_fragment.setArguments(bundle4);
                return thu_fragment;
            case 4:
               // FriFragment fri_fragment = new FriFragment();
                //return fri_fragment;
                FriFragment fri_fragment = new FriFragment();
                Bundle bundle5=new Bundle();
                fri_fragment.setArguments(bundle5);
                return fri_fragment;
            case 5:
                //SatFragment sat_fragment = new SatFragment();
               // return sat_fragment;
                SatFragment sat_fragment = new SatFragment();
                Bundle bundle6=new Bundle();
                sat_fragment.setArguments(bundle6);
                return sat_fragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}
