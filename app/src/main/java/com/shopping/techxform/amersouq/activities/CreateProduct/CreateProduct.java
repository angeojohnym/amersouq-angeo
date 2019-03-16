package com.shopping.techxform.amersouq.activities.CreateProduct;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.Products.CreateProductFragment;


public class CreateProduct extends TopBarPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_adpage);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_create_product, null, false);
        root_layout.addView(contentView, 0);


//        CreationAdFragment creationAdFragment=new CreationAdFragment();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, creationAdFragment);
////        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


        CreateProductFragment createProductFragment=new CreateProductFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, createProductFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}