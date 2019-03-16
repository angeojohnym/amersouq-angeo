package com.shopping.techxform.amersouq.activities.ViewAdvertisement;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.view_ads.ViewAllAdsFragment;
import com.shopping.techxform.amersouq.fragments.view_ads.ViewMyAdsFragment;

public class ViewAllAds extends TopBarPage {

    String type="my";
    String cat_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_all_ads);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_view_all_ads, null, false);
        root_layout.addView(contentView, 0);


        try {
            type=getIntent().getStringExtra("type");
        }catch (Exception e){
            type="my";
        }

        if(type.equals("all")) {

            ViewAllAdsFragment viewAdsFragment = new ViewAllAdsFragment();
            cat_id=getIntent().getStringExtra("cat_id");
            Bundle bundle=new Bundle();
            bundle.putString("cat_id",cat_id);
            viewAdsFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, viewAdsFragment);
//        fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            ViewMyAdsFragment viewAdsFragment = new ViewMyAdsFragment();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, viewAdsFragment);
//        fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }




    }
}
