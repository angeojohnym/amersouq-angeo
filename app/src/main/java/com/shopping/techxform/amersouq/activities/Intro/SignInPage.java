package com.shopping.techxform.amersouq.activities.Intro;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shopping.techxform.amersouq.fragments.registration.LoginFragment;
import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;

import im.delight.android.location.SimpleLocation;

public class SignInPage extends AppCompatActivity {

    SimpleLocation location;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(HomePage.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            //Camera Intent and access Location logic here
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(SignInPage.this, "Permission Denied\n" + deniedPermissions.toString() + "\n Kindly allow all the permissions", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        getwindowsproperty();

        location = new SimpleLocation(this);

        if (!location.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            new TedPermission(this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .check();
        }
        LoginFragment loginFragment=new LoginFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
