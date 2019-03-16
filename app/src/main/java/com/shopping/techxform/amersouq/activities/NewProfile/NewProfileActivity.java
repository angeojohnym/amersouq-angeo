package com.shopping.techxform.amersouq.activities.NewProfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.shopping.techxform.amersouq.Models.ProfileModel;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.profile_image_out.ProfileImageOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.adapters.ProfileAdapter;
import com.shopping.techxform.amersouq.adapters.VendorProfileAdapter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class NewProfileActivity extends AppCompatActivity {

    TextView profileBtn, vendorprofileBtn;
    RecyclerView rvProfile;
    ProfileAdapter profileAdapter;
    VendorProfileAdapter vendorProfileAdapter;
    CircularImageView iv_profile_img_change_btn, iv_profile_img;
    private List<ProfileModel> profileModels;
    private List<ProfileModel> vendorMrofileModels;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile_page);

        getwindowsproperty();

        profileBtn = (TextView) findViewById(R.id.btn_profile);
        vendorprofileBtn = (TextView) findViewById(R.id.btn_vendor_profile);
        iv_profile_img_change_btn = (CircularImageView) findViewById(R.id.iv_profile_img_change_btn);
        iv_profile_img = (CircularImageView) findViewById(R.id.iv_profile_img);

        rvProfile = (RecyclerView) findViewById(R.id.rv_profile);
        profileBtn.setSelected(true);
        vendorprofileBtn.setSelected(false);
        prepareprofileModel();

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(this, 3);
        rvProfile.setLayoutManager(mLayoutManager);
        profileAdapter = new ProfileAdapter(this, profileModels);
        rvProfile.setAdapter(profileAdapter);
        prepareVendorProfileModel();
        vendorProfileAdapter = new VendorProfileAdapter(this, vendorMrofileModels);
//        rvProfile.setAdapter(VendorProfileAdapter);
//
        SharedPreferences preferences=getSharedPreferences(Constants.pref_name,MODE_PRIVATE);
        String image_url=preferences.getString(Constants.image_url,"");

        RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
        Glide.with(NewProfileActivity.this).load(image_url)
                .apply(requestOption)
                .into(iv_profile_img);
        iv_profile_img_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null)
                        .setCropShape(CropImageView.CropShape.OVAL).setAspectRatio(10, 10)
                        .setGuidelines(CropImageView.Guidelines.ON).start(NewProfileActivity.this);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvProfile.setAdapter(profileAdapter);
                prepareprofileModel();
                profileBtn.setSelected(true);
                vendorprofileBtn.setSelected(false);
                vendorprofileBtn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                profileBtn.setTextColor(getResources().getColor(R.color.white));
                profileAdapter.notifyBtnClick(profileModels);
            }
        });

        vendorprofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareVendorProfileModel();
                rvProfile.setAdapter(vendorProfileAdapter);
                profileBtn.setSelected(false);
                vendorprofileBtn.setSelected(true);
                vendorprofileBtn.setTextColor(getResources().getColor(R.color.white));
                profileBtn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                vendorProfileAdapter.notifyBtnClick(vendorMrofileModels);
            }
        });
    }

    private void prepareVendorProfileModel() {
        vendorMrofileModels = new ArrayList<>();
        ProfileModel vendorProfileModel = new ProfileModel("Add Product", R.drawable.ads_product);
        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("View Product", R.drawable.package_enrolled);
        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("Feedback", R.drawable.chat_nav_icon);
        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("Alerts", R.drawable.alert_icon);
        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("OrderDetails", R.drawable.chat_nav_icon);
        vendorMrofileModels.add(vendorProfileModel);
//        vendorProfileModel = new ProfileModel("Billing Details", R.drawable.billing_details);
//        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("Messages", R.drawable.chat_nav_icon);
        vendorMrofileModels.add(vendorProfileModel);
        vendorProfileModel = new ProfileModel("Attribute Set", R.drawable.chat_nav_icon);
        vendorMrofileModels.add(vendorProfileModel);
    }

    private void prepareprofileModel() {
        profileModels = new ArrayList<>();
        ProfileModel profileModel = new ProfileModel("Profile Details", R.drawable.name_details);
        profileModels.add(profileModel);
        profileModel = new ProfileModel("Packages", R.drawable.package_enrolled);
        profileModels.add(profileModel);
        profileModel = new ProfileModel("Messages", R.drawable.chat_nav_icon);
        profileModels.add(profileModel);
        profileModel = new ProfileModel("Alerts", R.drawable.alert_icon);
        profileModels.add(profileModel);
        profileModel = new ProfileModel("Language", R.drawable.chat_nav_icon);
        profileModels.add(profileModel);
//        profileModel = new ProfileModel("Order Details", R.drawable.chat_nav_icon);
//        profileModels.add(profileModel);
        profileModel = new ProfileModel("Add Ads", R.drawable.chat_nav_icon);
        profileModels.add(profileModel);
        profileModel = new ProfileModel("View Ads", R.drawable.chat_nav_icon);
        profileModels.add(profileModel);

//        profileModel = new ProfileModel("Feedback", R.drawable.chat_nav_icon);
//        profileModels.add(profileModel);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri selectedImageURI = result.getUri();

                String filePath = getRealPathFromURI(result.getUri());
                upload_images(filePath);
//                int count=all_imageslist.size()+1;
//                all_imageslist.add(new AdImagesModel(Integer.toString(count),filePath,selectedImageURI));
//                update_imagelistview();

                Glide.with(NewProfileActivity.this)
                        .load(selectedImageURI)
                        .into(iv_profile_img);
//                File f = new File(getRealPathFromURI(result.getUri()));
            } else {

                Toast.makeText(NewProfileActivity.this, "Image not captured..", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
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

    public void upload_images(String filePath1) {

        final KProgressHUD hud1 = KProgressHUD.create(NewProfileActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();

        int userid = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0);
        RequestBody ad_id_body = RequestBody.create(MediaType.parse("multipart/form-data"), Integer.toString(userid));

        File file = new File(filePath1);
//        try {
//            file = new Compressor(getActivity()).compressToFile(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Call<ProfileImageOutput> call;
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        RequestBody requestImageFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part_image = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestImageFile);
        call = apiService.upload_profile_image(ad_id_body, part_image);


        call.enqueue(new Callback<ProfileImageOutput>() {
            @Override
            public void onResponse(Call<ProfileImageOutput> call, retrofit2.Response<ProfileImageOutput> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                try {
                    ProfileImageOutput uploadOutput = response.body();
                    String status_code = uploadOutput.getCode();
                    if (status_code.equals("200")) {
                        hud1.dismiss();
                        SharedPreferences preferences=getSharedPreferences(Constants.pref_name,MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString(Constants.image_url,uploadOutput.getImageUrl());
                        editor.apply();
                        Toast.makeText(NewProfileActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();


                    } else {
                        hud1.dismiss();

                        Toast.makeText(NewProfileActivity.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    hud1.dismiss();

                    Toast.makeText(NewProfileActivity.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProfileImageOutput> call, Throwable t) {
                hud1.dismiss();

//                Toast.makeText(HistoryPage.this,"Something went wrong..Try again after some time",Toast.LENGTH_SHORT).show();
                Toast.makeText(NewProfileActivity.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();


            }
        });


    }


}
