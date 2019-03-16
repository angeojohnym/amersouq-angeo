package com.shopping.techxform.amersouq.activities.Intro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.UtilMethods;
import com.shopping.techxform.amersouq.activities.Home.HomePage;

public class SplashScreen extends Activity {
    Activity activity;
    private static final int TIME = 3000;
    SharedPreferences sharedPreferences;

    ImageView logo_imgvw;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getwindowsproperty();
        activity=this;
        logo_imgvw=(ImageView)findViewById(R.id.logo_imgvw);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
//        logo_imgvw.startAnimation(animation);
//        tv.startAnimation(animation);

//        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
//        scale.setDuration(2000);
//        scale.setInterpolator(new OvershootInterpolator());
//        logo_imgvw.startAnimation(scale);

        sharedPreferences=getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(Constants.current_language, Constants.english_language).equals(Constants.english_language)) {

            new UtilMethods().setLocale(Constants.english_language,activity);


        } else {


            new UtilMethods().setLocale(Constants.arabic_language,activity);

        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {


                if (sharedPreferences.getBoolean(Constants.login_status,false)) {
                    Intent intent = new Intent(SplashScreen.this, HomePage.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, SetLanguagePage.class);
                    startActivity(intent);
                }

                finish();


            }
        }, TIME);

    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
