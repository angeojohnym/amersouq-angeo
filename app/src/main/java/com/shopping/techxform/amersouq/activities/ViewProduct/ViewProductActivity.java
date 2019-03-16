package com.shopping.techxform.amersouq.activities.ViewProduct;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.Products.ViewProductFragment;


public class ViewProductActivity extends TopBarPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_all_ads);
//    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

//    }/
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_view_full_product, null, false);
        root_layout.addView(contentView, 0);



        ViewProductFragment viewProductFragment=new ViewProductFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, viewProductFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();





    }
}
