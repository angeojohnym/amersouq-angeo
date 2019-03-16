package com.shopping.techxform.amersouq.activities.Category;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.view_ads.ViewAdCategoryFragment;

public class SelectCategoryPage extends TopBarPage {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select_category_page);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_select_category_page, null, false);
        root_layout.addView(contentView, 0);

        ViewAdCategoryFragment viewAdCategoryFragment = new ViewAdCategoryFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, viewAdCategoryFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }
}
