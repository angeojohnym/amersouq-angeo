package com.shopping.techxform.amersouq.activities.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Alerts.AlertsPage;
import com.shopping.techxform.amersouq.activities.CreateAdvertisement.CreateAdpage;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.activities.Intro.PaymentMethods;
import com.shopping.techxform.amersouq.activities.Intro.SetLanguagePage;
import com.shopping.techxform.amersouq.activities.Intro.SignInPage;
import com.shopping.techxform.amersouq.activities.Intro.PackagesActivity;
import com.shopping.techxform.amersouq.activities.Payment.PaymentDetailsPage;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewAllAds;

public class ProfilePage extends AppCompatActivity {

    ImageView back_btn;
    RelativeLayout name_details_layout,packages_lay_id,chats_layout_id,alerts_layout,add_pdt_layout,view_pdt_layout,order_layout,
                    pay_details_layout,feedback_layout,language_layout,logout_layout;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        getwindowsproperty();

        back_btn = (ImageView) findViewById(R.id.back_btn);
        name_details_layout = (RelativeLayout) findViewById(R.id.name_details_layout);
        packages_lay_id = (RelativeLayout) findViewById(R.id.packages_lay_id);
        chats_layout_id = (RelativeLayout) findViewById(R.id.chats_layout_id);
        alerts_layout = (RelativeLayout) findViewById(R.id.alerts_layout);

        add_pdt_layout = (RelativeLayout) findViewById(R.id.add_pdt_layout);
        view_pdt_layout = (RelativeLayout) findViewById(R.id.view_pdt_layout);
        order_layout = (RelativeLayout) findViewById(R.id.order_layout);
        pay_details_layout = (RelativeLayout) findViewById(R.id.pay_details_layout);

        feedback_layout = (RelativeLayout) findViewById(R.id.feedback_layout);
        language_layout = (RelativeLayout) findViewById(R.id.language_layout);
        logout_layout = (RelativeLayout) findViewById(R.id.logout_layout);


        if (getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0) != 0) {
            back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, HomePage.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });

            name_details_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, EditProfilePage.class);
                    startActivity(intent);
                }
            });

            pay_details_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, PaymentMethods.class);
                    startActivity(intent);
                }
            });

            packages_lay_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, PackagesActivity.class);
                    startActivity(intent);
                }
            });

            language_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, SetLanguagePage.class);
                    startActivity(intent);
                }
            });

            add_pdt_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent intent=new Intent(ProfilePage.this,CreateProduct.class);
//                startActivity(intent);

                    Intent intent = new Intent(ProfilePage.this, CreateAdpage.class);
                    startActivity(intent);

                }
            });

            view_pdt_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, ViewAllAds.class);

                    intent.putExtra("type", "my");
                    startActivity(intent);
                }
            });
            order_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, PaymentDetailsPage.class);
                    startActivity(intent);
                }
            });
            alerts_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfilePage.this, AlertsPage.class);
                    startActivity(intent);
                }
            });

            logout_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.login_status, false);
                    editor.putInt(Constants.user_id, 0);
                    editor.putString(Constants.first_name, "");
                    editor.apply();
                    Intent intent = new Intent(ProfilePage.this, SignInPage.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ProfilePage.this,HomePage.class);
        startActivity(intent);
        finishAffinity();

    }
}
