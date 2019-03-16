package com.shopping.techxform.amersouq.activities.Home;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.savefcmOutput.SavefcmOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.AddToCartActivity;
import com.shopping.techxform.amersouq.activities.CreateAdvertisement.CreateAdpage;
import com.shopping.techxform.amersouq.activities.Intro.SetLanguagePage;
import com.shopping.techxform.amersouq.activities.Intro.SignInPage;
import com.shopping.techxform.amersouq.activities.Intro.TopBarPage;
import com.shopping.techxform.amersouq.activities.NewProfile.NewProfileActivity;
import com.shopping.techxform.amersouq.activities.ProductsCartPage;
import com.shopping.techxform.amersouq.activities.Profile.ProfilePage;
import com.shopping.techxform.amersouq.fragments.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import devlight.io.library.ntb.NavigationTabBar;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;

public class HomePage extends TopBarPage {

    BottomSheetLayout bottomSheet;
    Context context;


    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(HomePage.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            //Camera Intent and access Location logic here
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(HomePage.this, "Permission Denied\n" + deniedPermissions.toString() + "\n Kindly allow all the permissions", Toast.LENGTH_SHORT).show();
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
//        setContentView(R.layout.activity_home_page);

        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home_page, null, false);
        root_layout.addView(contentView, 0);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }

        context = this;
        FirebaseApp.initializeApp(context);

        getwindowsproperty();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            new TedPermission(this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE)
                    .check();
        }
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        HomeFragment homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


//        bottomSheet.showWithSheetView(LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, bottomSheet, false));


        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home_icon),
                        ContextCompat.getColor(context, R.color.colorPrimary))
                        .selectedIcon(getResources().getDrawable(R.drawable.home_icon))
                        .title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.profile_icon),
                        ContextCompat.getColor(context, R.color.colorPrimary))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Profile")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.add_product_nav_icon),
                        ContextCompat.getColor(context, R.color.colorPrimary))
                        .selectedIcon(getResources().getDrawable(R.drawable.add_product_nav_icon))
                        .title("Post Ad")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_cart),
                        ContextCompat.getColor(context, R.color.colorPrimary))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_cart))
                        .title("Cart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.more_icon),
                        ContextCompat.getColor(context, R.color.colorPrimary))
                        .selectedIcon(getResources().getDrawable(R.drawable.more_icon))
                        .title("More")
                        .build()
        );

        navigationTabBar.setModels(models);


        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {
//                Toast.makeText(HomePage.this,"start"+Integer.toString(index),Toast.LENGTH_SHORT).show();

                if (index == 4) {
                    View view = inflater.inflate(R.layout.layout_menu_more, null);
                    new BottomDialog.Builder(HomePage.this)
                            .setTitle("Personalise!")
                            .setContent("Choose your preferences in the easy access menu ")
                            .setCustomView(view,0,0,0,0)
                            .show();
                    RelativeLayout name_layout=(RelativeLayout)view.findViewById(R.id.name_details_layout);
                    name_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(HomePage.this, NewProfileActivity.class);
                            intent.putExtra("flag", "AdPost");
                            startActivity(intent);
                        }
                    });
                    RelativeLayout language_layout=(RelativeLayout)view.findViewById(R.id.language_layout);
                    language_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(HomePage.this, SetLanguagePage.class);
                            intent.putExtra("flag", "AdPost");
                            startActivity(intent);
                        }
                    });
                    RelativeLayout logout_layout=(RelativeLayout)view.findViewById(R.id.logout_layout);
                    logout_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HomePage.this, SweetAlertDialog.WARNING_TYPE);
                            sweetAlertDialog.setTitle("Logout");
                            sweetAlertDialog.setContentText("Are you sure?");
                            sweetAlertDialog.setCancelText("Cancel");
                            sweetAlertDialog.setConfirmText("Logout");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    SharedPreferences preferences=getSharedPreferences(Constants.pref_name,MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferences.edit();
                                    editor.clear();
                                    editor.apply();
                                    Intent intent = new Intent(HomePage.this, SignInPage.class);
                                    intent.putExtra("flag", "AdPost");
                                    startActivity(intent);
                                    finishAffinity();                                }
                            });
                            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });
                            sweetAlertDialog.show();
                        }




                    });

//                    bottomSheet.showWithSheetView(LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, bottomSheet, false));
//
//                    RelativeLayout add_pdt_layout = (RelativeLayout) bottomSheet.findViewById(R.id.add_pdt_layout);
//                    add_pdt_layout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(HomePage.this, CreateAdpage.class);
//                            intent.putExtra("flag", "AdPost");
//                            startActivity(intent);
//                        }
//                    });
//
//                    RelativeLayout name_details_layout = (RelativeLayout) bottomSheet.findViewById(R.id.name_details_layout);
//                    add_pdt_layout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(HomePage.this, ProfilePage.class);
//                            intent.putExtra("flag", "AdPost");
//                            startActivity(intent);
//                        }
//                    });

                }
                if (index == 2) {
                    Intent intent = new Intent(HomePage.this, CreateAdpage.class);
                    intent.putExtra("flag", "AdPost");
                    startActivity(intent);
                }

                if (index == 1) {
//                    Intent intent = new Intent(HomePage.this, ProfilePage.class);
//                    startActivity(intent);
                    Intent intent = new Intent(HomePage.this, NewProfileActivity.class);
                    startActivity(intent);
                }

                if (index == 3) {

//                    Intent intent1 = new Intent(HomePage.this, AddToCartActivity.class);
//                    startActivity(intent1);
                    Intent intent1 = new Intent(HomePage.this, ProductsCartPage.class);
                    startActivity(intent1);

//                    Intent intent = new Intent(HomePage.this, NewProfileActivity.class);
//                    startActivity(intent);
                }
            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {

            }
        });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = token;
                        Log.d(TAG, msg);
                        save_fcm(msg);
//                        Toast.makeText(HomePage.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });



    }

    public void save_fcm(String fcm_token){


        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String ime = telephonyManager.getDeviceId();
//        iccid = telephonyManager.getSimSerialNumber();
        int user_id = 0;
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        user_id = sharedPreferences.getInt(Constants.user_id, 0);
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        if (user_id != 0) {
            Call<SavefcmOutput> call = apiService.save_fcm_token(Integer.toString(user_id),fcm_token,ime);

            call.enqueue(new Callback<SavefcmOutput>() {
                @Override
                public void onResponse(Call<SavefcmOutput> call, retrofit2.Response<SavefcmOutput> response) {

                    String URL = call.request().url().toString();
                    System.out.println("Retrofit URL : " + URL);
                    if (response.code() == 200) {
//
                        Log.d("FCM_TOKEN", "succesfully sent");

                    } else {
                        Log.d("FCM_TOKEN", "failure sent");
                    }


                }

                @Override
                public void onFailure(Call<SavefcmOutput> call, Throwable t) {
                    Log.d("FCM_TOKEN", "fail sent");
                }
            });
        }




    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if (getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0) == 0) {

            Intent intent = new Intent(HomePage.this, SignInPage.class);
            startActivity(intent);
            finishAffinity();

        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HomePage.this, SweetAlertDialog.WARNING_TYPE);
            sweetAlertDialog.setTitle("Exit?");
            sweetAlertDialog.setContentText("Are you sure to exit");
            sweetAlertDialog.setCancelText("Cancel");
            sweetAlertDialog.setConfirmText("Exit");
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    finishAffinity();
                }
            });
            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    sweetAlertDialog.dismissWithAnimation();
                }
            });
            sweetAlertDialog.show();
        }
    }
}
