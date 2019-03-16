package com.shopping.techxform.amersouq.activities.ProfileEdit;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.Models.ProfileResponse;
import com.shopping.techxform.amersouq.Models.ProfileUpdateRequest;
import com.shopping.techxform.amersouq.Models.ProfileUpdateResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ProfileEditActivity extends BaseActivity {

    private Context mContext;
    private ImageView profilePic;
    private TextView name;
    private TextView mail;
    private TextView mobile;
    private TextView almobile;
    private TextView gender;
    private TextView location;
    private TextView address;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getwindowsproperty();
        init();

    }

    private void init() {
        mContext = this;
        profilePic = findViewById(R.id.iv_profile_img);
        name = findViewById(R.id.input_name);
        mail = findViewById(R.id.input_email);
        mobile = findViewById(R.id.input_mobilee);
        almobile = findViewById(R.id.input_alternate_mobile);
        gender = findViewById(R.id.input_alternate_gender);
        location = findViewById(R.id.input_alternate_location);
        address = findViewById(R.id.input_address);
        getProfile();
    }

    private void getProfile() {

        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ProfileResponse> call = apiService.getProfileDetails(38);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                if (profileResponse != null && profileResponse.getUser_profile() != null && profileResponse.getUser_profile().size() > 0) {
                    if (profileResponse.getUser_profile().get(0).getProfile_pic() != null) {
                        Glide.with(mContext).load(profileResponse.getUser_profile().get(0).getProfile_pic())
                                .into(profilePic);
                    }

                    name.setText(String.format("%s %s", profileResponse.getUser_profile().get(0).getFirst_name(), profileResponse.getUser_profile().get(0).getLast_name()));
                    mail.setText(profileResponse.getUser_profile().get(0).getEmail());
                    mobile.setText(profileResponse.getUser_profile().get(0).getContact_number());
                    almobile.setText(profileResponse.getUser_profile().get(0).getContact_number());
                    location.setText(profileResponse.getUser_profile().get(0).getCity());
                    address.setText(profileResponse.getUser_profile().get(0).getAddress());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {


            }
        });
    }

    private void updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ProfileUpdateResponse> call = apiService.updateProfileDetails(profileUpdateRequest);

        call.enqueue(new Callback<ProfileUpdateResponse>() {
            @Override
            public void onResponse(Call<ProfileUpdateResponse> call, retrofit2.Response<ProfileUpdateResponse> response) {
                ProfileUpdateResponse profileResponse = response.body();
                if (profileResponse != null && profileResponse.getData() != null && profileResponse.getData().size() > 0) {
                    if (profileResponse.getCode().equalsIgnoreCase("200"))
                        Toast.makeText(ProfileEditActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateResponse> call, Throwable t) {


            }
        });
    }

    public void onClickSubmit(View view) {
        String firstName = "";
        String lastName = "";
        if (!name.getText().toString().trim().isEmpty()) {
            String[] names = name.getText().toString().trim().split(" ");

            firstName = names[0];
            if (names[1] != null)
                lastName = names[1];
        }
        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();
        profileUpdateRequest.setAddress(address.getText().toString().trim());
        profileUpdateRequest.setFirst_name(firstName);
        profileUpdateRequest.setLast_name(lastName);
        profileUpdateRequest.setEmail(mail.getText().toString().trim());
        profileUpdateRequest.setContact_number(mobile.getText().toString().trim());
        profileUpdateRequest.setCountry_code("+91");

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
       int uid=sharedPreferences.getInt(Constants.user_id,0);
        profileUpdateRequest.setUserid(String.valueOf(uid));
        updateProfile(profileUpdateRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_main_setting) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
