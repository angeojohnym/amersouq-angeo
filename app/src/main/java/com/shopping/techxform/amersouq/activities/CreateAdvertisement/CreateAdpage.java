package com.shopping.techxform.amersouq.activities.CreateAdvertisement;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.fragments.ad_creation.PostCategoryFragment;

import java.util.ArrayList;

public class CreateAdpage extends TopBarPage {

    private String flag = "";

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(HomePage.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            //Camera Intent and access Location logic here
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(CreateAdpage.this, "Permission Denied\n" + deniedPermissions.toString() + "\n Kindly allow all the permissions", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_adpage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_create_adpage, null, false);
        root_layout.addView(contentView, 0);
        getwindowsproperty();
        flag = getIntent().getStringExtra("flag");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            new TedPermission(this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .check();
        }

//        CreationAdFragment creationAdFragment=new CreationAdFragment();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, creationAdFragment);
////        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

//
//        SelectCategoryFragment selectCategoryFragment=new SelectCategoryFragment();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, selectCategoryFragment);
////        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        PostCategoryFragment postCategoryFragment = new PostCategoryFragment().newInstance(flag);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, postCategoryFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}
