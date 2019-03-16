package com.shopping.techxform.amersouq.activities.AddProduct;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.Models.AddProductResponse;
import com.shopping.techxform.amersouq.Models.ProductRequest;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AddProduct;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.AllLanguage;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.LanguagesOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {

    AllCategory category_selected = null;
    String lan_id = "0", main_cat = "";
    String language = "";
    RelativeLayout rlAdditionalInfoLayoutl, rlPaymentLayout, rlShipingLayout;
    ExpandableRelativeLayout expandableLayout, elPayment, elShipping;
    MaterialSpinner language_spinner;
    EditText input_addesc, input_adtitle, input_fulldesc, input_price, input_offerprice, input_number,
            input_address, input_condition;
    CheckBox boost_ad_checkbox, check_box_feature;
    TextView submit_btn;
    int boostPoduct = -1, featureProduct = -1;
    Toolbar mToolbar;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        getwindowsproperty();

        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar=getSupportActionBar();
        if (mActionBar!=null){
            mActionBar.setTitle("Add Product");
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        rlAdditionalInfoLayoutl = (RelativeLayout) findViewById(R.id.rl_additional_info_layout);
        rlPaymentLayout = (RelativeLayout) findViewById(R.id.rl_payment_method_layout);
        rlShipingLayout = (RelativeLayout) findViewById(R.id.rl_shipping_layout);
        expandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        elPayment = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_payment);
        elShipping = (ExpandableRelativeLayout) findViewById(R.id.el_shiping);
        language_spinner = findViewById(R.id.language_spinner);
        boost_ad_checkbox = findViewById(R.id.boost_ad_checkbox);
        check_box_feature = findViewById(R.id.check_box_feature);
        input_addesc = findViewById(R.id.input_addesc);
        input_adtitle = findViewById(R.id.input_adtitle);
        input_fulldesc = findViewById(R.id.input_fulldesc);
        input_offerprice = findViewById(R.id.input_offerprice);
        input_number = findViewById(R.id.input_number);
        input_address = findViewById(R.id.input_address);
        input_price = findViewById(R.id.input_price);
        input_condition = findViewById(R.id.input_condition);
        submit_btn = findViewById(R.id.submit_btn);


        Bundle bundle = getIntent().getBundleExtra("bundil");
        assert bundle != null;
        category_selected = (AllCategory) bundle.getSerializable("category_selected");
        main_cat = bundle.getString("main_cat");

        rlAdditionalInfoLayoutl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.toggle();
            }
        });

        rlPaymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elPayment.toggle();
            }
        });

        rlShipingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elShipping.toggle();
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });


        set_languages(language_spinner);
        boost_ad_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    boostPoduct = 1;
                } else {
                    boostPoduct = 0;
                }
            }
        });

        check_box_feature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    featureProduct = 1;
                } else {
                    featureProduct = 0;
                }
            }
        });

    }


    public void set_languages(final MaterialSpinner lan_spinner) {

        final ArrayList<SpinnerModel> lan_list = new ArrayList<>();
        lan_list.add(new SpinnerModel("0", "Select Language"));
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<LanguagesOutput> call = apiService.get_languages();

        call.enqueue(new Callback<LanguagesOutput>() {
            @Override
            public void onResponse(Call<LanguagesOutput> call, retrofit2.Response<LanguagesOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    LanguagesOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<AllLanguage> allLanguages = new ArrayList<>();
                            allLanguages = responseBody.getAllLanguages();
                            for (int i = 0; i < allLanguages.size(); i++) {
                                lan_list.add(new SpinnerModel(allLanguages.get(i).getLanguageId(), allLanguages.get(i).getLanguage()));
                            }

                            lan_spinner.setItems(lan_list);
                            lan_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                    SpinnerModel spinnerModel = (SpinnerModel) item;
                                    lan_id = spinnerModel.getId();
                                    language = spinnerModel.getName();
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
            public void onFailure(Call<LanguagesOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    private void addProduct() {

        AddProduct addProduct = new AddProduct(input_adtitle.getText().toString()
                , input_addesc.getText().toString(), input_fulldesc.getText().toString(), boostPoduct, featureProduct, 1,
                language, Float.parseFloat(input_price.getText().toString()), Float.parseFloat(input_offerprice.getText().toString()),
                input_number.getText().toString(), input_address.getText().toString(),
                input_condition.getText().toString(), "1", 0f, 0f, 0f, "Cit Name",
                Integer.parseInt(category_selected.getCategoryId()));
        ProductRequest productRequest=new ProductRequest();
        productRequest.setProduct_name(input_adtitle.getText().toString());
        productRequest.setShort_desc(input_addesc.getText().toString());
        productRequest.setFull_desc(input_fulldesc.getText().toString());
        productRequest.setBoost_status(String.valueOf(0));
        productRequest.setFeatured_product(String.valueOf(1));
        productRequest.setAvailable_to_guest(String.valueOf(1));
        String lan="EN";
        if (!language.equalsIgnoreCase("English")){
            lan="AR";
        }
        productRequest.setLanguage(lan);
        productRequest.setPrice(input_price.getText().toString());
        productRequest.setOffer_price(input_offerprice.getText().toString());
        productRequest.setContact(input_number.getText().toString());
        productRequest.setAddress(input_address.getText().toString());
        productRequest.setCondition(input_condition.getText().toString());
        productRequest.setCityname("Thrissur");
        productRequest.setPackage_type("1");
        productRequest.setCategory_id(category_selected.getCategoryId());
        productRequest.setBreadth("9");
        productRequest.setWeight("34");
        productRequest.setHeight("7");
        productRequest.setLength("5");
        productRequest.setLatitude("10.2121");
        productRequest.setLongitude("76.323");
//        {
//            "product_name": "product",
//                "short_desc": "short_description",
//                "full_desc": "full_description",
//                "boost_status": "0",
//                "featured_product": "1",
//                "available_to_guest": "1",
//                "language": "EN",
//                "price": "32",
//                "offer_price": "12",
//                "contact": "98956565",
//                "address": "address",
//                "condition": "refurbished",
//                "package_type": "1",
//                "weight": "34",
//                "breadth": "9",
//                "height": "7",
//                "length": "8",
//                "latitude": "10.2121",
//                "longitude": "76.323",
//                "cityname": "Thrissur",
//                "category_id": "20",
//                "created_user_id": "1"
//        }



        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
       int userId=sharedPreferences.getInt(Constants.user_id,0);
        addProduct.setCreated_user_id(userId);
        productRequest.setCreated_user_id(String.valueOf(userId));
        System.out.println("REQUEST==>"+new Gson().toJson(productRequest));
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<JsonObject> call = apiService.addNewProductNew(productRequest);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hud.dismiss();
                JsonObject jsonObject = response.body();
                System.out.println("ADD RESPONSE==>"+new Gson().toJson(jsonObject));
                System.out.println("ADD RESPONSE==>"+response.code());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println("ADD RESPONSE==>"+t.toString());
            }
        });


    }

}
