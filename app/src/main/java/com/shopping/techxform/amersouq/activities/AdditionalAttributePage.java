package com.shopping.techxform.amersouq.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product.AttributeProductInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product.AttributeProductOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AttributeSelectedModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributeSetOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributesItem;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.adapters.AttributeSetAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AdditionalAttributePage extends AppCompatActivity {

    Activity activity;
    Context context;
    RecyclerView attributes_rv;
    TextView submit_btn;
    String cat_id = "0", product_id = "0";

    ArrayList<AttributeSelectedModel> selectedModels = new ArrayList<>();
    AttributeSetAdapter attributeSetAdapter;
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_attribute_page);
        getwindowsproperty();
        attributes_rv = (RecyclerView) findViewById(R.id.attributes_rv);
        submit_btn = (TextView) findViewById(R.id.submit_btn);

        activity = this;
        context = this;

        cat_id = getIntent().getStringExtra("cat_id");
        product_id = getIntent().getStringExtra("product_id");
        viewattributeSet(cat_id);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveattributeSet(product_id);
                for (AttributeSelectedModel selectedModel : selectedModels) {
                    Toast.makeText(AdditionalAttributePage.this, selectedModel.getAttribute_id() + " " + selectedModel.getValue(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewattributeSet(String cat_id) {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<AttributeSetOutput> call = apiService.get_attribute_set(cat_id);

        call.enqueue(new Callback<AttributeSetOutput>() {
            @Override
            public void onResponse(Call<AttributeSetOutput> call, retrofit2.Response<AttributeSetOutput> response) {

                hud.dismiss();
                AttributeSetOutput attributeSetOutput = response.body();
                System.out.println(new Gson().toJson(attributeSetOutput));
                if (attributeSetOutput.getCode().equals("200")) {
                    if (attributeSetOutput != null && attributeSetOutput.getAttributes() != null && attributeSetOutput.getAttributes().size() > 0) {

                        for (AttributesItem item : attributeSetOutput.getAttributes()) {
                            selectedModels.add(new AttributeSelectedModel(item.getAttributeId(), "0"));
                        }

                        attributeSetAdapter = new AttributeSetAdapter(attributeSetOutput.getAttributes(), activity, context);
                        attributes_rv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        attributes_rv.setItemAnimator(new DefaultItemAnimator());
                        attributes_rv.setNestedScrollingEnabled(false);
                        attributes_rv.setAdapter(attributeSetAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<AttributeSetOutput> call, Throwable t) {

                hud.dismiss();
            }
        });
    }

    private void saveattributeSet(String product_id) {
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        List<AttributeProductInput> attributeProductInputs = new ArrayList<>();
        for (int i = 0; i < selectedModels.size(); i++) {
            attributeProductInputs.add(new AttributeProductInput(selectedModels.get(i).getAttribute_id(), product_id, selectedModels.get(i).getValue()));
        }
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<AttributeProductOutput> call = apiService.set_product_attributes(attributeProductInputs);

        call.enqueue(new Callback<AttributeProductOutput>() {
            @Override
            public void onResponse(Call<AttributeProductOutput> call, retrofit2.Response<AttributeProductOutput> response) {
                hud.dismiss();
                AttributeProductOutput attributeSetOutput = response.body();
                System.out.println(new Gson().toJson(attributeSetOutput));
                if (attributeSetOutput.getCode().equals("200")) {

                    Toast.makeText(AdditionalAttributePage.this, "Successfully updated the product", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdditionalAttributePage.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AttributeProductOutput> call, Throwable t) {

                hud.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AdditionalAttributePage.this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitle("Confirm to go back");
        sweetAlertDialog.setContentText("Attribute data will not be saved");
        sweetAlertDialog.setCancelText("Cancel");
        sweetAlertDialog.setConfirmText("Exit");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                Intent intent = new Intent(AdditionalAttributePage.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();


    }

    public void set_option(String att_id, String option) {
        for (AttributeSelectedModel model : selectedModels) {
            if (model.getAttribute_id().equals(att_id)) {
                model.setValue(option);
                break;
            }
        }
    }


}
