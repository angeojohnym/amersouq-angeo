package com.shopping.techxform.amersouq.fragments.ad_creation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AdCoordinatesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AdLocationModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.CreateAuctionInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Classified_Ad_Update.Classs_Ad_Update;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ConditionsData.AllCondition;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ConditionsData.ConditionsOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CreateAd.CreateClassifiedOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LocationsData.AllLocation;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LocationsData.LocationsOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductImageUpload.ImageUploadOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ShippingPackage.AllShippingpackage;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ShippingPackage.ShippingPackageOutput;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.CreateAdTransferModel;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.activities.Intro.SignInPage;
import com.shopping.techxform.amersouq.adapters.AdImageAuctionAdapter;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by techxform on 30-Nov-18.
 */


public class AuctionFragment extends Fragment implements OnMapReadyCallback {

    CheckBox auto_bid_cb, homepagelive_cb;
    Calendar myCalendar ;

    String latitude_string = "", longitude_string = "", city_string = "", address_string = "",from_date_string="",to_date_string="",from_time_string="",to_time_string="";
    ScrollView mainScrollView;
    View transparentImageView;
    TextView shipping_details_td, locationMarkertext;
    LinearLayout shipping_layout;
    LinearLayout bid_layout;
    Activity activity;
    CreateAdTransferModel transferModel = null;
    MaterialSpinner location_spinner, condition_spinner, shiping_spinner;
    Context context;
    TextView price_td, offer_price_td, contact_td, address_td, submit_btn;
    int loc_id = 0, condition_id = 0, userid, shipping_id = 0;
    String filePath, auto_bid = "",Category;
    ImageView upload_img_btn;
    EditText baseprice_td, reserveprice_td, starts_from_td, ends_at_td, pricelimit_td, increment_td;
    EditText from_date,to_date,from_time,to_time;
    RecyclerView image_list;
    TextView length_td, breadth_td, width_td, weight_td;
    HomeAdapter ascollectionAdapter;
    List<String> ascollection_listitems = new ArrayList<>();
    ArrayList<AdImagesModel> all_imageslist = new ArrayList<>();
    AdImageAuctionAdapter adImagesAdapter = null;
    String live_auction = "0",Homelive;
    AuctionFragment auctionFragment;
    Double lati = 0.0, longi = 0.0;
    GoogleMap map;
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.auction_fragment, container, false);
        getwindowsproperty();

        auto_bid_cb = view.findViewById(R.id.auto_bid_cb);
        homepagelive_cb = view.findViewById(R.id.homepagelive_cb);

        if (homepagelive_cb.isChecked()){

            Homelive="1";

        }else {

            Homelive="2";

        }

        bid_layout = view.findViewById(R.id.bid_layout);
        FragmentManager mFragmentManager = this.getChildFragmentManager();
        MapFragment MapFragment = (MapFragment) mFragmentManager.findFragmentById(R.id.map);
        MapFragment.getMapAsync(this);

        Bundle bundle = getArguments();
        transferModel = (CreateAdTransferModel) bundle.getSerializable("transfer_data");
        location_spinner = view.findViewById(R.id.location_spinner);
        condition_spinner = view.findViewById(R.id.condition_spinner);
        shiping_spinner = view.findViewById(R.id.shipping_pack_spinner);
        price_td = view.findViewById(R.id.price_td);
        offer_price_td = view.findViewById(R.id.offer_price_td);
        upload_img_btn = view.findViewById(R.id.upload_img_btn);
        submit_btn = view.findViewById(R.id.submit_btn);
        locationMarkertext = (TextView) view.findViewById(R.id.locationMarkertext);
        mainScrollView = (ScrollView) view.findViewById(R.id.main_scrollview);
        transparentImageView = (View) view.findViewById(R.id.transparent_image);
        shipping_details_td = view.findViewById(R.id.shipping_details_td);
        shipping_layout = view.findViewById(R.id.shipping_layout);

        from_date = view.findViewById(R.id.from_date);
        to_date = view.findViewById(R.id.to_date);
        from_time = view.findViewById(R.id.from_time);
        to_time = view.findViewById(R.id.to_time);


        length_td = view.findViewById(R.id.length_td);
        breadth_td = view.findViewById(R.id.breadth_td);
        width_td = view.findViewById(R.id.width_td);
        weight_td = view.findViewById(R.id.weight_td);

        baseprice_td = view.findViewById(R.id.baseprice_td);
        reserveprice_td = view.findViewById(R.id.reserveprice_td);

        starts_from_td = view.findViewById(R.id.starts_from_td);
        ends_at_td = view.findViewById(R.id.ends_at_td);
        pricelimit_td = view.findViewById(R.id.pricelimit_td);
        increment_td = view.findViewById(R.id.increment_td);


        contact_td = view.findViewById(R.id.contact_td);
        address_td = view.findViewById(R.id.address_td);
        image_list = (RecyclerView) view.findViewById(R.id.images_recyclerview);
        userid = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0);
        auctionFragment = this;
        activity = getActivity();
        context = getActivity();

        Category=transferModel.getCategory_Type();
        if (Category.equals("3")) {
            contact_td.setText(transferModel.getContactinfo());
            contact_td.setEnabled(false);
            starts_from_td.setText(transferModel.getStart_From());
            starts_from_td.setEnabled(false);
            baseprice_td.setText(transferModel.getBest_Price());
            baseprice_td.setEnabled(false);
            reserveprice_td.setText(transferModel.getReserved_Price());
            reserveprice_td.setEnabled(false);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String formattedDate = df.format(c.getTime());
            // formattedDate have current date/time
            String date = formattedDate;
            String[] dateParts = date.split(" ");
            String day = dateParts[0];
            String time = dateParts[1];

            from_date.setText(day);
            from_time.setText(time);
            from_time.setEnabled(false);
            from_date.setEnabled(false);
        }
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(13.0f);
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextColor(getResources().getColor(R.color.black));
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setHintTextColor(getResources().getColor(R.color.black));
        setInitialView();

        myCalendar = Calendar.getInstance();
        upload_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(getActivity(), auctionFragment);

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0) == 0) {

                    Intent intent = new Intent(getActivity(), SignInPage.class);
                    startActivity(intent);
                    getActivity().finishAffinity();

                } else {
//                    if (all_imageslist.size() == 0) {
//
//                        Toast.makeText(getActivity(), "You need too upload atleast one image of the product", Toast.LENGTH_SHORT).show();
//                    } else {

//                        if(from_date_string.equals("")||to_date_string.equals("")||from_time_string.equals("")||to_time_string.equals("")){
//                            Toast.makeText(getActivity(),"Select all date and time",Toast.LENGTH_SHORT).show();
//                        }
//                        else{
                            if (Category.equals("1")){
                                Update_Address();
                            }else {
                                create_auction();
//                            }
//                    }
 }
                }
            }
        });


        shipping_details_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shipping_layout.getVisibility() == View.GONE) {
                    shipping_layout.setVisibility(View.VISIBLE);
                } else {
                    shipping_layout.setVisibility(View.GONE);

                }
            }
        });

        homepagelive_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    live_auction = "1";
                } else {
                    live_auction = "0";

                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                from_date_string=sdf.format(myCalendar.getTime());
                from_date.setText(from_date_string);
            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                to_date_string=sdf.format(myCalendar.getTime());
                to_date.setText(to_date_string);

            }

        };




        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        final TimePickerDialog from_time_picker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                from_time.setText( selectedHour + ":" + selectedMinute+":00");
                from_time_string=selectedHour + ":" + selectedMinute+":00";
                from_time.setText(from_time_string);
            }
        }, hour, minute, true);//Yes 24 hour time

        final TimePickerDialog to_time_picker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                to_time_string=selectedHour + ":" + selectedMinute+":00";
                to_time.setText(to_time_string);
            }
        }, hour, minute, true);//Yes 24 hour time

        from_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                from_time_picker.setTitle("Select Time");
                from_time_picker.show();
            }
        });
        to_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_time_picker.setTitle("Select Time");
                to_time_picker.show();
            }
        });






        auto_bid_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    auto_bid = "1";
                    bid_layout.setVisibility(View.VISIBLE);
                } else {
                    auto_bid = "0";
                    bid_layout.setVisibility(View.GONE);

                }
            }
        });

        transparentImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());//get place details here
//                city_td.setText(place.getName().toString());

//                map.clear();

                // Animating to the touched position


                LatLng latLng = place.getLatLng();
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.animateCamera(CameraUpdateFactory.zoomTo(13.0f));


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        return view;


    }


    private void Update_Address(){




        final KProgressHUD hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        Call<Classs_Ad_Update> call = apiService.update_auction(transferModel.getAd_id(),transferModel.getAd_title(),transferModel.getAd_short_desc(),
                transferModel.getAd_desc(),Homelive,"1");

        call.enqueue(new Callback<Classs_Ad_Update>() {
            @Override
            public void onResponse(Call<Classs_Ad_Update> call, retrofit2.Response<Classs_Ad_Update> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    Classs_Ad_Update responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            getActivity().finish();

                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                            System.out.println("SHANIL ::: 2 " + status);

                        } else {



                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<Classs_Ad_Update> call, Throwable t) {

                hud.dismiss();

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri selectedImageURI = result.getUri();

                filePath = getRealPathFromURI(result.getUri());
                int count = all_imageslist.size() + 1;
                all_imageslist.add(new AdImagesModel(Integer.toString(count), filePath, selectedImageURI));
                update_imagelistview();

//                Glide.with(getActivity())
//                        .load(selectedImageURI)
//                        .into(pdct_imgvw);
//                File f = new File(getRealPathFromURI(result.getUri()));
            } else {

                Toast.makeText(getActivity(), "Image not captured..", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    public void setInitialView() {
        set_locations();
        set_conditions();
        set_shippingpackage();
    }

    public void set_locations() {

        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<LocationsOutput> call = apiService.get_locations();

        call.enqueue(new Callback<LocationsOutput>() {
            @Override
            public void onResponse(Call<LocationsOutput> call, retrofit2.Response<LocationsOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    LocationsOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<AllLocation> allLocations = new ArrayList<>();
                            allLocations = responseBody.getAllLocations();
                            for (int i = 0; i < allLocations.size(); i++) {
                                loc_list.add(new SpinnerModel(allLocations.get(i).getLocationId(), allLocations.get(i).getLocationName()));
                            }

                            location_spinner.setItems(loc_list);
                            location_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                    SpinnerModel spinnerModel = (SpinnerModel) item;
                                    loc_id = Integer.parseInt(spinnerModel.getId());
                                }

                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<LocationsOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void set_conditions() {

        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
        loc_list.add(new SpinnerModel("0", "Select Condition"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ConditionsOutput> call = apiService.get_conditions();

        call.enqueue(new Callback<ConditionsOutput>() {
            @Override
            public void onResponse(Call<ConditionsOutput> call, retrofit2.Response<ConditionsOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    ConditionsOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<AllCondition> allConditions = new ArrayList<>();
                            allConditions = responseBody.getAllConditions();
                            for (int i = 0; i < allConditions.size(); i++) {
                                loc_list.add(new SpinnerModel(allConditions.get(i).getConditionId(), allConditions.get(i).getConditionName()));
                            }

                            condition_spinner.setItems(loc_list);
                            condition_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                    SpinnerModel spinnerModel = (SpinnerModel) item;
                                    condition_id = Integer.parseInt(spinnerModel.getId());
                                }

                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<ConditionsOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void set_shippingpackage() {

        final ArrayList<SpinnerModel> shipping_list = new ArrayList<>();
        shipping_list.add(new SpinnerModel("0", "Select Shipping Package"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ShippingPackageOutput> call = apiService.get_shippingpackage();

        call.enqueue(new Callback<ShippingPackageOutput>() {
            @Override
            public void onResponse(Call<ShippingPackageOutput> call, retrofit2.Response<ShippingPackageOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    ShippingPackageOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<AllShippingpackage> allConditions = new ArrayList<>();
                            allConditions = responseBody.getAllShippingpackage();
                            for (int i = 0; i < allConditions.size(); i++) {
                                shipping_list.add(new SpinnerModel(allConditions.get(i).getShipPackageId(), allConditions.get(i).getPackageName()));
                            }

                            shiping_spinner.setItems(shipping_list);
                            shiping_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                    SpinnerModel spinnerModel = (SpinnerModel) item;
                                    shipping_id = Integer.parseInt(spinnerModel.getId());
                                }

                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<ShippingPackageOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    public void create_auction() {
//        CreateAuctionInput createAuctionInput = new CreateAuctionInput(transferModel.getLanguage(), transferModel.getAd_title(), transferModel.getAd_short_desc(), transferModel.getAd_desc(),
//                transferModel.getVisible_state(), price_td.getText().toString(), offer_price_td.getText().toString(), Integer.parseInt(transferModel.getCat_id()), userid,
//                address_td.getText().toString(), contact_td.getText().toString(), condition_id, loc_id, shipping_id,
//                transferModel.getMain_cat(),length_td.getText().toString(),breadth_td.getText().toString(),width_td.getText().toString(),weight_td.getText().toString(),
//                starts_from_td.getText().toString(),ends_at_td.getText().toString(),auto_bid,baseprice_td.getText().toString(),
//                pricelimit_td.getText().toString(),reserveprice_td.getText().toString());

        String weight="0";
        if(weight_td.getText().toString().trim().equals("")){
            weight="0.0";
        }else {
            weight=weight_td.getText().toString();
        }
        CreateAuctionInput createAuctionInput = new CreateAuctionInput(transferModel.getLanguage(), transferModel.getAd_title(), transferModel.getAd_short_desc(),
                transferModel.getAd_desc(),
                transferModel.getVisible_state(), price_td.getText().toString(), offer_price_td.getText().toString(), Integer.parseInt(transferModel.getCat_id()), userid,
                address_td.getText().toString(), contact_td.getText().toString(), condition_id, loc_id, shipping_id,
                transferModel.getMain_cat(), length_td.getText().toString(), breadth_td.getText().toString(), weight, width_td.getText().toString(),
               from_date_string+" "+from_time_string,to_date_string+" "+to_time_string, live_auction, baseprice_td.getText().toString(),
                "0", reserveprice_td.getText().toString());

        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<CreateClassifiedOutput> call = apiService.create_auction(createAuctionInput);

        call.enqueue(new Callback<CreateClassifiedOutput>() {
            @Override
            public void onResponse(Call<CreateClassifiedOutput> call, retrofit2.Response<CreateClassifiedOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    CreateClassifiedOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
                            int i = 1;
                            String ad_id_current = Integer.toString(responseBody.getAdId());
                            Toast.makeText(getActivity(), "Uploading Images", Toast.LENGTH_SHORT).show();

                            for (AdImagesModel adImagesModel : all_imageslist) {
                                upload_images(ad_id_current, adImagesModel.getFile_path(), i);
                                i++;
                            }


                        } else {
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<CreateClassifiedOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    public void upload_images(final String ad_id, String filePath1, final int img_count) {

        final KProgressHUD hud1 = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();

        RequestBody ad_id_body = RequestBody.create(MediaType.parse("multipart/form-data"), ad_id);

        File file = new File(filePath1);
        try {
            file = new Compressor(getActivity()).compressToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Call<ImageUploadOutput> call;
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        RequestBody requestImageFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part_image = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestImageFile);
        call = apiService.upload_ad_image(ad_id_body, part_image);


        call.enqueue(new Callback<ImageUploadOutput>() {
            @Override
            public void onResponse(Call<ImageUploadOutput> call, retrofit2.Response<ImageUploadOutput> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                try {
                    ImageUploadOutput uploadOutput = response.body();
                    String status_code = uploadOutput.getCode();
                    if (status_code.equals("200")) {
                        hud1.dismiss();

                        if (img_count == all_imageslist.size()) {
                            update_ad_location(ad_id);
//                            Intent intent=new Intent(activity, HomePage.class);
//                            getActivity().startActivity(intent);
////                        activity.finishAffinity();
                            Toast.makeText(getActivity(), "Successfully processed", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        hud1.dismiss();

                        Toast.makeText(getActivity(), "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    hud1.dismiss();

                    Toast.makeText(getActivity(), "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ImageUploadOutput> call, Throwable t) {
                hud1.dismiss();

//                Toast.makeText(HistoryPage.this,"Something went wrong..Try again after some time",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void removefromlist(AdImagesModel adImagesModel) {
        all_imageslist.remove(adImagesModel);
//        adImagesAdapter.notifyDataSetChanged();
        update_imagelistview();
    }

    public void update_imagelistview() {

        if (all_imageslist.size() > 0) {
            image_list.setVisibility(View.VISIBLE);
        } else {
            image_list.setVisibility(View.GONE);
        }
        image_list.setAdapter(null);
        adImagesAdapter = new AdImageAuctionAdapter(all_imageslist, activity, context, auctionFragment);
        image_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        image_list.setItemAnimator(new DefaultItemAnimator());
        image_list.setNestedScrollingEnabled(false);
        image_list.setAdapter(adImagesAdapter);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        Toast.makeText(getActivity(),"kjshjs",Toast.LENGTH_SHORT).show();
        lati = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getString(Constants.curr_latitude, ""));
        longi = Double.parseDouble(getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getString(Constants.curr_longitude, ""));

//        Glide.with(context).load(R.drawable.ripple_marker).into(new GlideDrawableImageViewTarget(IMAGE_VIEW));

        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(lati, longi);
        markerOptions.position(latLng);
        map.clear();

        // Animating to the touched position

        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d("Camera postion change" + "", cameraPosition + "");
                LatLng mCenterLatLong = cameraPosition.target;


                map.clear();

                try {

                    Location mLocation = new Location("");
                    mLocation.setLatitude(mCenterLatLong.latitude);
                    mLocation.setLongitude(mCenterLatLong.longitude);
                    latitude_string = Double.toString(mCenterLatLong.latitude);
                    longitude_string = Double.toString(mCenterLatLong.longitude);

//                    startIntentService(mLocation);
                    locationMarkertext.setText("Lat : " + mCenterLatLong.latitude + "," + "Long : " + mCenterLatLong.longitude);
                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    List<Address> addresses = null;

                    addresses = geocoder.getFromLocation(mCenterLatLong.latitude, mCenterLatLong.longitude, 1);
                    try {
                        city_string = addresses.get(0).getLocality();
                        address_string = addresses.get(0).getAddressLine(0);
                        locationMarkertext.setText(address_string + " " + addresses.get(0).getLocality() + addresses.get(0).getSubAdminArea());
                    } catch (Exception e) {
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void update_ad_location(String ad_id) {


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        AdLocationModel adLocationModel;
        adLocationModel = new AdLocationModel(latitude_string, longitude_string, address_string, ad_id, city_string);

        Call<AdCoordinatesOutput> call = apiService.update_ad_location(adLocationModel);

        call.enqueue(new Callback<AdCoordinatesOutput>() {
            @Override
            public void onResponse(Call<AdCoordinatesOutput> call, retrofit2.Response<AdCoordinatesOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    AdCoordinatesOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
                            Toast.makeText(getActivity(), "Ad posted successfully", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(activity, HomePage.class);
                        getActivity().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Intent intent = new Intent(activity, HomePage.class);
                        getActivity().startActivity(intent);
                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<AdCoordinatesOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


}
