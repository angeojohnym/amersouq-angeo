package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.CategoryAdapter;
import com.shopping.techxform.amersouq.fragments.amer_souq_collections.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 07-Jan-19.
 */

public class ViewAdCategoryFragment extends Fragment {
    Activity activity;
    Context context;
    RecyclerView category_list;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> category_listitems = new ArrayList<>();
    List<Integer> drawables = new ArrayList<>();

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_select_category, container, false);
        getwindowsproperty();
        activity = getActivity();
        context = getActivity();
        category_list = view.findViewById(R.id.cat_list);

        category_listitems.clear();


        category_listitems.add(new CategoryModel(R.drawable.car_cat, "Motors", "34"));
        category_listitems.add(new CategoryModel(R.drawable.building_cat, "Property", "35"));
        category_listitems.add(new CategoryModel(R.drawable.businessman_cat, "Jobs", "36"));
        category_listitems.add(new CategoryModel(R.drawable.laundry_cat, "For Sale", "37"));
        category_listitems.add(new CategoryModel(R.drawable.delivery_cat, "Services", "38"));


        categoryAdapter = new CategoryAdapter(category_listitems, activity, R.layout.category_large_item, context);
        category_list.setLayoutManager(new GridLayoutManager(context, 2));
        category_list.setItemAnimator(new DefaultItemAnimator());
        category_list.setNestedScrollingEnabled(false);
        category_list.setAdapter(categoryAdapter);


        return view;

    }
}




