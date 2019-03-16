package com.shopping.techxform.amersouq.activities.ProductList;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shopping.techxform.amersouq.fragments.CustomerRequest.CustomerRequestFragment;
import com.shopping.techxform.amersouq.fragments.ProductList.ProductListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
//        if (position == 0)
//        {
//            fragment = new ProductListFragment();
//        }
         if (position == 0)
        {
            fragment = new CustomerRequestFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
//        if (position == 0)
//        {
//            title = "MY ORDERS";
//        }
//        else
            if (position == 0)
        {
            title = "CUSTOMER REQUEST";
        }
        return title;
    }
}
