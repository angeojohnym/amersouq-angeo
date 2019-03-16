package com.shopping.techxform.amersouq.fragments.UpdateLocation;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;

import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by techxform on 11-Jan-19.
 */


public class UpdateLocFragment extends DialogFragment implements OnMapReadyCallback {
    private static View view;
    TextView locationMarkertext, location_tv,select_lati_td,select_longi_td;
    GoogleMap map;
    SharedPreferences preferences;
    MapFragment mapFragment;
    Double latitude = 0.0, longitude = 0.0;
    String cityName = "";
    TextView place_td, city_td, curr_location_td;
    List<Address> addresses = null;
    TextView update_location;
    Geocoder geocoder;
    UpdateLocFragment locFragment;

    public UpdateLocFragment() {
        // Required empty public constructor
    }

    public static UpdateLocFragment newInstance(int myIndex) {
        UpdateLocFragment updateLocFragment = new UpdateLocFragment();

        //example of passing args
        Bundle args = new Bundle();
        args.putInt("anIntToSend", myIndex);
        updateLocFragment.setArguments(args);

        return updateLocFragment;
    }

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //read the int from args
        int myInteger = getArguments().getInt("anIntToSend");
//        View view = inflater.inflate(R.layout.update_location_layout, null);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        getwindowsproperty();
        preferences = getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.update_location_layout, container, false);
//            FragmentManager mFragmentManager = this.getFragmentManager();
//             mapFragment = (MapFragment) mFragmentManager.findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        locFragment = this;
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        city_td = (TextView) view.findViewById(R.id.city_td);
        place_td = (TextView) view.findViewById(R.id.place_td);

        locationMarkertext = (TextView) view.findViewById(R.id.locationMarkertext);

        update_location = (TextView) view.findViewById(R.id.update_location);
        location_tv = (TextView) getActivity().findViewById(R.id.location_tv);
        curr_location_td = (TextView) view.findViewById(R.id.curr_location_td);
        select_lati_td=(TextView)view.findViewById(R.id.select_lati_td);
        select_longi_td=(TextView)view.findViewById(R.id.select_longi_td);

        update_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dismiss();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.selected_latitude, select_lati_td.getText().toString());
                editor.putString(Constants.selected_longitude, select_longi_td.getText().toString());
                editor.putString(Constants.selected_placename, city_td.getText().toString());
                editor.putBoolean(Constants.location_current, false);
                editor.apply();
                location_tv.setText(city_td.getText().toString());
                dismissAllowingStateLoss();


            }
        });

        curr_location_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_current_location();
            }
        });
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());//get place details here

                if (map != null) {
                    LatLng latLng = place.getLatLng();
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    map.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
                } else {

                    mapFragment.getMapAsync(locFragment);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            LatLng latLng = place.getLatLng();
                            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            map.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
                            Toast.makeText(getActivity(), "map returning null", Toast.LENGTH_SHORT).show();

                        }
                    }, 1000);

                }

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        Toast.makeText(getActivity(), "kjshjs", Toast.LENGTH_SHORT).show();
        Double lati = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.curr_latitude, "29.3117"));
        Double longi = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.curr_longitude, "47.4818"));



//        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(lati, longi);

//        markerOptions.position(latLng);
////        map.clear();

        // Animating to the touched position

        try {
            if(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getBoolean(Constants.location_current,true)) {
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
            }
            else
            {
                Double select_lati=Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.selected_latitude, "29.3117"));
                Double select_longi=Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.selected_longitude, "47.4818"));
                LatLng select_latLng = new LatLng(select_lati, select_longi);
                map.moveCamera(CameraUpdateFactory.newLatLng(select_latLng));
                map.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
            }

        }catch (Exception e){
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            map.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
        }
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d("Camera postion change" + "", cameraPosition + "");
                LatLng mCenterLatLong = cameraPosition.target;

                try {

                    Location mLocation = new Location("");
                    mLocation.setLatitude(mCenterLatLong.latitude);
                    mLocation.setLongitude(mCenterLatLong.longitude);

//                    locationMarkertext.setText("Lat :" + mCenterLatLong.latitude + ", " + "Long :" + mCenterLatLong.longitude);
                    select_lati_td.setText(Double.toString(mCenterLatLong.latitude));
                    select_longi_td.setText(Double.toString(mCenterLatLong.longitude));

                    addresses = geocoder.getFromLocation(mCenterLatLong.latitude, mCenterLatLong.longitude, 1);

                    try {
                        String address_string = addresses.get(0).getAddressLine(0);
                        place_td.setText(address_string);
                        String city = "";
//                            String cityName = addresses.get(0).getAddressLine(0);
                        String stateName = addresses.get(0).getLocality();
                        String countryName = addresses.get(0).getCountryName();
                        city = addresses.get(0).getSubAdminArea();
                        if (city != null) {
                            city_td.setText(city);
                        } else if (stateName != null) {
//                                city=addresses.get(0).getCountryName();
                            city_td.setText(stateName);
                        } else if (countryName != null) {
//                                city=addresses.get(0).getThoroughfare();
                            city_td.setText(countryName);
                        }


                    } catch (Exception e) {

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void set_current_location() {
        Double lati = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.curr_latitude, ""));
        Double longi = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, MODE_PRIVATE).getString(Constants.curr_longitude, ""));


        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(lati, longi);
        markerOptions.position(latLng);


        if (map != null) {
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            map.animateCamera(CameraUpdateFactory.zoomTo(13.0f));
        } else {
            Toast.makeText(getActivity(), "map returning null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mapFragment1;
        if (Build.VERSION.SDK_INT < 21) {
            mapFragment1 = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
        } else {
            mapFragment1 = (MapFragment) this.getFragmentManager()
                    .findFragmentById(R.id.map);
        }
        mapFragment1.getMapAsync(this);
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        MapFragment f = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        if (f != null){
//            getFragmentManager().beginTransaction().remove(f).commit();
//        }
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (null != mapFragment)
//            getFragmentManager().beginTransaction()
//                    .remove(mapFragment)
//                    .commit();
//    }

}