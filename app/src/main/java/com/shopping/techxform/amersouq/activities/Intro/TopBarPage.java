package com.shopping.techxform.amersouq.activities.Intro;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData.SearchAd;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData.SearchOuputModel;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.fragments.UpdateLocation.UpdateLocFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;

import retrofit2.Call;
import retrofit2.Callback;

public class TopBarPage extends AppCompatActivity implements View.OnClickListener {
    protected RelativeLayout root_layout;
    protected TextView location_tv;
    protected ImageView loc_imgvw;
    private SimpleLocation location;
    SharedPreferences preferences;
    LinearLayout location_layout;
    TextView searchtext;
    private List<SearchAd> suggestions = new ArrayList<>();
    private CustomSuggestionsAdapter customSuggestionsAdapter;
//    MaterialSearchBar searchBar;
    Activity activity;
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_page);
        getwindowsproperty();
        activity=this;
        location = new SimpleLocation(this);
//        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customSuggestionsAdapter = new CustomSuggestionsAdapter(inflater,activity);

//        searchBar.setMaxSuggestionCount(2);
//        searchBar.setHint("Find Product..");
        root_layout = (RelativeLayout) findViewById(R.id.root_layout);
        location_tv = (TextView) findViewById(R.id.location_tv);
        loc_imgvw = (ImageView) findViewById(R.id.loc_imgvw);
        searchtext=findViewById(R.id.searchtext);
        location_layout = (LinearLayout) findViewById(R.id.location_layout);
        preferences = getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        if (!location.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }
//
        location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateLocFragment updateLocFragment = UpdateLocFragment.newInstance(0);
                updateLocFragment.show(getFragmentManager().beginTransaction(), "DialogFragment");
            }
        });
//        for (int i = 1; i < 11; i++) {
//            suggestions.add(new SearchAd("sfdfsdfd","ewgrgegee",""));
//        }

//        customSuggestionsAdapter.setSuggestions(suggestions);
//        searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter);
////        loc_imgvw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UpdateLocFragment updateLocFragment = UpdateLocFragment.newInstance(0);
//                updateLocFragment.show(getFragmentManager().beginTransaction(), "DialogFragment");
//            }
//        });

//        searchBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        set_search();
//            }
//        });


        /*searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
                // send the entered text to our filter and let it manage everything
                customSuggestionsAdapter.getFilter().filter(searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });*/

//
//        spinnerDialog = new SpinnerDialog(TopBarPage.this, items,
//                "Select or Search City");
//
//        spinnerDialog.setCancellable(true);
//        spinnerDialog.setShowKeyboard(false);
//
//        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String item, int position) {
//                Toast.makeText(TopBarPage.this, item + "  " + position + "", Toast.LENGTH_SHORT).show();
//                selectedItems.setText(item + " Position: " + position);
//            }
//        });
//
//        findViewById(R.id.searchtext).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinnerDialog.showSpinerDialog();
//            }
//        });


    }



//    @Override
//    public void onClick(View view) {
//        customSuggestionsAdapter.addSuggestion(new SearchAd("Product", 100));
//    }




    @Override
    protected void onResume() {
        super.onResume();

        try {

            if(!preferences.getBoolean(Constants.location_current,true)) {
                location_tv.setText(preferences.getString(Constants.selected_placename,""));
            }
            // make the device update its location
            location.beginUpdates();

            if(location!=null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;
                String cityName="";
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                try {
                    cityName = addresses.get(0).getLocality();
                    if(preferences.getBoolean(Constants.location_current,true)) {
                        location_tv.setText(cityName);
                    }
                } catch (Exception e) {
                }

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(Constants.curr_latitude,Double.toString(latitude));
                editor.putString(Constants.curr_longitude,Double.toString(longitude));
                editor.putString(Constants.curr_placename,cityName);
                editor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        editor.putString(utilsClass.latitude_txt,Double.toString(latitude));
//        editor.putString(utilsClass.longitude_txt,Double.toString(longitude));
//        editor.apply();

//        Toast.makeText(ClientListingActivity.this,Double.toString(latitude),Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPause() {
        // stop location updates (saves battery)
        location.endUpdates();

        super.onPause();
    }

    public void   set_search() {



        final KProgressHUD hud = KProgressHUD.create(TopBarPage.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<SearchOuputModel> call = apiService.get_search_items();

        call.enqueue(new Callback<SearchOuputModel>() {
            @Override
            public void onResponse(Call<SearchOuputModel> call, retrofit2.Response<SearchOuputModel> response) {
                hud.dismiss();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    final SearchOuputModel responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
//                            models=responseBody.getSearchAds();
                            customSuggestionsAdapter.setSuggestions(responseBody.getSearchAds());
//                            searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();

            }

            @Override
            public void onFailure(Call<SearchOuputModel> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    @Override
    public void onClick(View v) {
//        customSuggestionsAdapter.addSuggestion(new SearchAd("Product", "100",""));

    }
}




