package com.shopping.techxform.amersouq;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.google.firebase.FirebaseApp;

public class AmerSouqApplication extends Application {

    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        FirebaseApp.initializeApp(context);


    }


}
