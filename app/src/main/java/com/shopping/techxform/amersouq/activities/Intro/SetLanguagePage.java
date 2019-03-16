package com.shopping.techxform.amersouq.activities.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.orm.dsl.NotNull;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Home.HomePage;

import java.util.Locale;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class SetLanguagePage extends AppCompatActivity {
    StickySwitch stickySwitch;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor pref_editor;
    TextView sub_btn;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_language_page);

        getwindowsproperty();

        stickySwitch = (StickySwitch) findViewById(R.id.sticky_switch);
        sub_btn = (TextView) findViewById(R.id.sub_btn);


        sharedPreferences = getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        pref_editor = sharedPreferences.edit();

        if (sharedPreferences.getString(Constants.current_language, Constants.english_language).equals(Constants.english_language)) {
            stickySwitch.setDirection(StickySwitch.Direction.RIGHT, false);

        } else {

            stickySwitch.setDirection(StickySwitch.Direction.LEFT, false);
        }
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {

                if (direction.toString().equals("RIGHT")) {


                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            setLocale(Constants.english_language);

                        }
                    }, 600);

                } else {
//                    setLocale(Constants.arabic_language);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            setLocale(Constants.arabic_language);

                        }
                    }, 600);

                }

            }
        });

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (getIntent().getStringExtra("flag").equals("profile")) {
                        finish();
                    }
                    else {

                        Intent intent = new Intent(SetLanguagePage.this, HomePage.class);
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Intent intent = new Intent(SetLanguagePage.this, SignInPage.class);
                    startActivity(intent);
                }


            }
        });

    }

    public void setLocale(String lang) { //call this in onCreate()
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        pref_editor.putString(Constants.current_language, lang);
        pref_editor.apply();
        recreate();

        //Intent refresh = new Intent(this, AndroidLocalize.class);
        //startActivity(refresh);
        //finish();
    }
}
