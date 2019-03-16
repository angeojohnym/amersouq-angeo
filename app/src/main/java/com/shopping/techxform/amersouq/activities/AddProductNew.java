package com.shopping.techxform.amersouq.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.Models.CreateProductInputModel;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductImageUpload.ImageUploadOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductOutputModel;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.adapters.ProductImagesAdapter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AddProductNew extends AppCompatActivity {

    Activity activity;
    EditText input_adtitle, input_addesc, input_fulldesc, input_price, input_offerprice, stock_input;
    CheckBox check_box_feature;
    Context context;
    EditText from_date,to_date,from_time,to_time;
    String from_date_string="",to_date_string="",from_time_string="",to_time_string="";
    Calendar myCalendar ;

    ImageView add_img_btn;
    String filePath = "", condition_string = "Brand New", featured_string = "0";
    RecyclerView image_list;
    MaterialSpinner condition_spinner;
    ProductImagesAdapter productImagesAdapter = null;
    ArrayList<AdImagesModel> all_imageslist = new ArrayList<>();
    Toolbar mToolbar;
    TextView submit_btn;
    AllCategory category_selected = null;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_new);

        getwindowsproperty();

        image_list = (RecyclerView) findViewById(R.id.all_images_list);
        add_img_btn = (ImageView) findViewById(R.id.add_img_btn);

        submit_btn = (TextView) findViewById(R.id.submit_btn);
        check_box_feature = (CheckBox) findViewById(R.id.check_box_feature);


        from_date = (EditText) findViewById(R.id.from_date);
        to_date = (EditText)findViewById(R.id.to_date);
        from_time = (EditText)findViewById(R.id.from_time);
        to_time = (EditText)findViewById(R.id.to_time);

        input_adtitle = (EditText) findViewById(R.id.input_adtitle);
        input_addesc = (EditText) findViewById(R.id.input_addesc);
        input_fulldesc = (EditText) findViewById(R.id.input_fulldesc);

        input_price = (EditText) findViewById(R.id.input_price);
        input_offerprice = (EditText) findViewById(R.id.input_offerprice);
        stock_input = (EditText) findViewById(R.id.stock_input);

        condition_spinner = (MaterialSpinner) findViewById(R.id.condition_spinner);
        activity = this;
        context = this;
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle("Add Product");
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        myCalendar = Calendar.getInstance();

        Bundle bundle = getIntent().getBundleExtra("bundil");
        assert bundle != null;
        category_selected = (AllCategory) bundle.getSerializable("category_selected");
//        main_cat = bundle.getString("main_cat");
        add_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(AddProductNew.this);

            }
        });

        ArrayList<SpinnerModel> condition_list = new ArrayList<>();
        condition_list.add(new SpinnerModel("1", "Brand New"));
        condition_list.add(new SpinnerModel("2", "Refurbished"));

        condition_spinner.setItems(condition_list);
        condition_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                SpinnerModel spinnerModel = (SpinnerModel) item;
                condition_string = spinnerModel.getName();
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
                new DatePickerDialog(AddProductNew.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddProductNew.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        final TimePickerDialog from_time_picker = new TimePickerDialog(AddProductNew.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                from_time.setText( selectedHour + ":" + selectedMinute+":00");
                from_time_string=selectedHour + ":" + selectedMinute+":00";
                from_time.setText(from_time_string);
            }
        }, hour, minute, true);//Yes 24 hour time

        final TimePickerDialog to_time_picker = new TimePickerDialog(AddProductNew.this, new TimePickerDialog.OnTimeSetListener() {
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





        check_box_feature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    featured_string = "1";
                } else {
                    featured_string = "0";
                }
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(from_date_string.equals("")||to_date_string.equals("")||from_time_string.equals("")||to_time_string.equals("")){
                    Toast.makeText(AddProductNew.this,"Select all date and time",Toast.LENGTH_SHORT).show();
                }
                else{
                create_product();
            }}
        });

    }


    public void create_product() {
//        CreateClassifiedInput classifiedInput = new CreateClassifiedInput(transferModel.getLanguage(), transferModel.getAd_title(), transferModel.getAd_short_desc(), transferModel.getAd_desc(),
//                transferModel.getVisible_state(), price_td.getText().toString(), offer_price_td.getText().toString(), Integer.parseInt(transferModel.getCat_id()), userid,
//                address_td.getText().toString(), contact_td.getText().toString(), condition_id, loc_id, classified_type,
//                transferModel.getMain_cat());
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        String userId = Integer.toString(sharedPreferences.getInt(Constants.user_id, 0));

        CreateProductInputModel productInputModel = new CreateProductInputModel(input_adtitle.getText().toString().trim(), input_addesc.getText().toString().trim(), input_fulldesc.getText().toString(),
                featured_string, input_price.getText().toString().trim(), input_offerprice.getText().toString().trim(), condition_string, category_selected.getCategoryId(),
                userId, stock_input.getText().toString().trim(),from_date_string+" "+from_time_string,to_date_string+" "+to_time_string);

        final KProgressHUD hud = KProgressHUD.create(AddProductNew.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ProductOutputModel> call = apiService.add_pro(productInputModel);

        call.enqueue(new Callback<ProductOutputModel>() {
            @Override
            public void onResponse(Call<ProductOutputModel> call, retrofit2.Response<ProductOutputModel> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    ProductOutputModel responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
                            int i = 1;
                            String product_id_current = Integer.toString(responseBody.getData().getProductId());
                            Toast.makeText(AddProductNew.this, "Uploading Images", Toast.LENGTH_SHORT).show();

                            for (AdImagesModel adImagesModel : all_imageslist) {
                                upload_images(product_id_current, adImagesModel.getFile_path(), i);
                                i++;
                            }


                        } else {
                            Toast.makeText(AddProductNew.this, "Something went wrong.. Try again after sometime..", Toast.LENGTH_SHORT).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<ProductOutputModel> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    public void upload_images(final String product_id, String filePath1, final int img_count) {

        final KProgressHUD hud1 = KProgressHUD.create(AddProductNew.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();

        RequestBody ad_id_body = RequestBody.create(MediaType.parse("multipart/form-data"), product_id);

        File file = new File(filePath1);


        Call<ImageUploadOutput> call;
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        RequestBody requestImageFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part_image = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestImageFile);
        call = apiService.upload_product_image(ad_id_body, part_image);


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

                            Toast.makeText(AddProductNew.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductNew.this, AdditionalAttributePage.class);
                            intent.putExtra("cat_id",category_selected.getCategoryId());
                            intent.putExtra("product_id",product_id);
                            startActivity(intent);
                            finishAffinity();
                        }

                    } else {
                        hud1.dismiss();

                        Toast.makeText(AddProductNew.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    hud1.dismiss();

                    Toast.makeText(AddProductNew.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ImageUploadOutput> call, Throwable t) {
                hud1.dismiss();

//                Toast.makeText(HistoryPage.this,"Something went wrong..Try again after some time",Toast.LENGTH_SHORT).show();
                Toast.makeText(AddProductNew.this, "Something went wrong.. Try again after some time..", Toast.LENGTH_SHORT).show();


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

            } else {

                Toast.makeText(AddProductNew.this, "Image not captured..", Toast.LENGTH_SHORT).show();

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


    public void removefromlist(AdImagesModel adImagesModel) {
        all_imageslist.remove(adImagesModel);
//        adImagesAdapter.notifyDataSetChanged();
        update_imagelistview();
    }

    public void update_imagelistview() {


        image_list.setAdapter(null);
        productImagesAdapter = new ProductImagesAdapter(all_imageslist, activity, context);
        image_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        image_list.setItemAnimator(new DefaultItemAnimator());
        image_list.setNestedScrollingEnabled(false);
        image_list.setAdapter(productImagesAdapter);


    }

}
