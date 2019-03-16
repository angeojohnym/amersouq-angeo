package com.shopping.techxform.amersouq.activities.ViewAdvertisement;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.view_ads.AuctionViewAdFragment;
import com.shopping.techxform.amersouq.fragments.view_ads.ClassifiedViewFragment;
import com.shopping.techxform.amersouq.fragments.view_ads.FixedAdViewFragment;

public class ViewFullAd extends TopBarPage {

    String ad_type_id,ad_id,type="all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_view_full_ad, null, false);
        root_layout.addView(contentView, 0);

        ad_type_id="1";
        try {

            ad_type_id = getIntent().getStringExtra("ad_type_id");
            if(ad_type_id==null){
                ad_type_id="1";
            }
        }catch (Exception e){
        ad_type_id="1";
        }
        ad_id=getIntent().getStringExtra("ad_id");
        type=getIntent().getStringExtra("type");

        Bundle bundle=new Bundle();
        bundle.putString("ad_id",ad_id);
        bundle.putString("type",type);

        if(ad_type_id.equals("1")) {
            ClassifiedViewFragment classifiedViewFragment = new ClassifiedViewFragment();
            classifiedViewFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, classifiedViewFragment);
//        fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(ad_type_id.equals("2")){

            FixedAdViewFragment fixedAdViewFragment = new FixedAdViewFragment();
            fixedAdViewFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fixedAdViewFragment);
//        fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            AuctionViewAdFragment auctionViewAdFragment = new AuctionViewAdFragment();
            auctionViewAdFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, auctionViewAdFragment);
//        fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }



    }
}
