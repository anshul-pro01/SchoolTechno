package com.example.passwordnapoochiyo.schooltechno;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class MyAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String id;
    String s,r;
    public MyAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.id=id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                Payment_info tab1 = new Payment_info();
                Bundle bundle1=new Bundle();
                tab1.setArguments(bundle1);
                return tab1;
            case 1:
                Pay_fees tab2 = new Pay_fees();
                Bundle bundle=new Bundle();
                tab2.setArguments(bundle);
                return tab2;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
