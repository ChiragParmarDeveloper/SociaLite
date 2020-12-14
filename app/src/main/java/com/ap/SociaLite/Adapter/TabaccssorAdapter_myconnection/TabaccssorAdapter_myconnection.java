package com.ap.SociaLite.Adapter.TabaccssorAdapter_myconnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ap.SociaLite.Fragment.Connection.ConnectionFragment;
import com.ap.SociaLite.Fragment.Connection.MyConnectionFragment;

public class TabaccssorAdapter_myconnection extends FragmentPagerAdapter {

    public TabaccssorAdapter_myconnection(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0 :
                ConnectionFragment connectionFragment = new ConnectionFragment();
                return connectionFragment;

            case 1:
                MyConnectionFragment myConnectionFragment = new MyConnectionFragment();
                return myConnectionFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {

            case 0:
                return "Connection";
            case 1:
                return "My Connection";
            default:
                return null;
        }
    }


}
