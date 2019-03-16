package com.shopping.techxform.amersouq.activities.Intro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.shopping.techxform.amersouq.R;


public class DrawerLayoutPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected NavigationView mNavigationView;
//    ImageView open_nav_iv;
    protected RelativeLayout root_layout;
    DrawerLayout drawer_layout;
    RelativeLayout name_details_layout;
    View hView;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        getwindowsproperty();

        root_layout = (RelativeLayout) findViewById(R.id.root_layout);
//        open_nav_iv = (ImageView) findViewById(R.id.open_nav_iv);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        hView = (RelativeLayout) mNavigationView.findViewById(R.id.header_begin_lay);
        name_details_layout = (RelativeLayout) mNavigationView.findViewById(R.id.name_details_layout);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        open_nav_iv = (ImageView) findViewById(R.id.open_nav_iv);
//
//
//        open_nav_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawer_layout.openDrawer(Gravity.LEFT);
//            }
//        });

        name_details_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);

            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
