package com.shopping.techxform.amersouq.activities.Intro;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chahinem.pageindicator.PageIndicator;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;
import com.shopping.techxform.amersouq.adapters.PackagesAdapter;

import java.util.ArrayList;
import java.util.List;


public class PackagesActivity extends AppCompatActivity {

//    AnyViewIndicator recyclerViewIndicator;
    Activity activity;
    PageIndicator pageIndicator;
    Context context;
    List<String> allpackages=new ArrayList<>();
    PackagesAdapter packagesAdapter;
    RecyclerView packages_list;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        getwindowsproperty();

//        recyclerViewIndicator=(RecyclerViewIndicator)findViewById(R.id.circleIndicator);
        pageIndicator=(PageIndicator)findViewById(R.id.pageIndicator) ;
        activity=this;
        context=this;
        packages_list=(RecyclerView)findViewById(R.id.packages_list);


        for(int i=0;i<6;i++){

            allpackages.add("abc");

        }

        packagesAdapter=new PackagesAdapter(allpackages,activity,R.layout.package_list_item,context);
        packages_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        packages_list.setItemAnimator(new DefaultItemAnimator());
        packages_list.setNestedScrollingEnabled(false);
        packages_list.setAdapter(packagesAdapter);

        pageIndicator.attachTo(packages_list);

//        recyclerViewIndicator.setRecyclerView(packages_list);
////        recyclerViewIndicator.setCurrentPosition(4);
//        recyclerViewIndicator.forceUpdateItemCount();
//        recyclerViewIndicator.setItemCount(allpackages.size()); // set the total count of  indicator
//        recyclerViewIndicator.setCurrentPosition(0); //

//        packages_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
////                Toast.makeText(context,"fdfds",Toast.LENGTH_SHORT).show();
//
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE:
//
//                        int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                        Toast.makeText(context,Integer.toString(position),Toast.LENGTH_SHORT).show();
//                        recyclerViewIndicator.setCurrentPosition(position);
//                        break;
//                }
//            }
//        });
    }
}
