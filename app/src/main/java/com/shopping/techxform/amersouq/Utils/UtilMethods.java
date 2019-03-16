package com.shopping.techxform.amersouq.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by techxform on 22-Nov-18.
 */



public class UtilMethods {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor pref_editor;

    public void setLocale(String lang, Activity activity) {
        sharedPreferences=activity.getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        pref_editor=sharedPreferences.edit();
        Locale myLocale = new Locale(lang);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        pref_editor.putString(Constants.current_language, lang);
        pref_editor.apply();

    }



}


