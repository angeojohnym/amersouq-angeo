package com.shopping.techxform.amersouq.fragments.Products;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */


public class CreateProductFragment extends Fragment {
    Activity activity;
    Context context;
    RecyclerView image_list;
    HomeAdapter ascollectionAdapter;
    List<String> ascollection_listitems=new ArrayList<>();

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_create_product, container, false);
        getwindowsproperty();


        return view;

    }
}
