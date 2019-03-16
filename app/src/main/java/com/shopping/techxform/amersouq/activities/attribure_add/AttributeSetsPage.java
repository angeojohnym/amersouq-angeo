package com.shopping.techxform.amersouq.activities.attribure_add;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AttributeAddInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_output.AttributeAddOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributeSetOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.ViewAttributesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AttributeSetsPage extends AppCompatActivity {

    RecyclerView attribute_list;
    TextView cat_title;
    ViewAttributesAdapter viewAttributesAdapter;
    Activity activity;
    FloatingActionButton add_attribute;
    AllCategory category_selected;
    String attribute_type_string = "";

    Context context;
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_sets_page);

        getwindowsproperty();

        add_attribute = (FloatingActionButton) findViewById(R.id.add_attribute);
        cat_title = (TextView) findViewById(R.id.cat_title);
        attribute_list = (RecyclerView) findViewById(R.id.attribute_list);
        activity = this;
        context = this;


        Bundle bundle = getIntent().getBundleExtra("bundil");
        assert bundle != null;
        category_selected = (AllCategory) bundle.getSerializable("category_selected");


        cat_title.setText(category_selected.getCategoryName());

        add_attribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.attribute_add_layout, null);
                alertDialogBuilder.setView(view1);
                alertDialogBuilder.setCancelable(true);
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

                final EditText attribute_name_td = dialog.findViewById(R.id.attribute_name_td);
                MaterialSpinner attribute_type = dialog.findViewById(R.id.attributes_type);
                Button submit_btn = dialog.findViewById(R.id.submit_btn);
                List<SpinnerModel> type_list = new ArrayList<>();
                type_list.add(new SpinnerModel("0", "Select type"));
                type_list.add(new SpinnerModel("1", "DROPDOWN"));
                type_list.add(new SpinnerModel("2", "TEXT"));
                type_list.add(new SpinnerModel("3", "DATE"));
                type_list.add(new SpinnerModel("4", "BOOLEAN"));


                attribute_type.setItems(type_list);
                attribute_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                        if (position != 0) {
                            SpinnerModel spinnerModel = (SpinnerModel) item;
                            attribute_type_string = spinnerModel.getName();
                        }
                    }

                });

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(attribute_name_td.getText().toString().trim().equals("")) {

                            Toast.makeText(AttributeSetsPage.this,"Enter valid input",Toast.LENGTH_SHORT).show();
                        }
                        else if(attribute_type_string.equals("")){

                            Toast.makeText(AttributeSetsPage.this,"Select attribute type",Toast.LENGTH_SHORT).show();

                        }
                        else {

                            add_attribute(category_selected.getCategoryId(), attribute_name_td.getText().toString(),dialog);

                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewattributeSet(category_selected.getCategoryId());
    }

    public void viewattributeSet(final String cat_id) {


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
                if(response.code()==200)
               {

                   if(response.body().getCode().equals("200")){
                    AttributeSetOutput attributeSetOutput = response.body();
                    System.out.println(new Gson().toJson(attributeSetOutput));

                    if (attributeSetOutput != null && attributeSetOutput.getAttributes() != null && attributeSetOutput.getAttributes().size() > 0) {


                        viewAttributesAdapter = new ViewAttributesAdapter(attributeSetOutput.getAttributes(), activity, context,cat_id);
                        attribute_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        attribute_list.setItemAnimator(new DefaultItemAnimator());
                        attribute_list.setNestedScrollingEnabled(false);
                        attribute_list.setAdapter(viewAttributesAdapter);
                    }
                }}
            }

            @Override
            public void onFailure(Call<AttributeSetOutput> call, Throwable t) {

                hud.dismiss();
            }
        });
    }

    private void add_attribute(final String cat_id, String attribute_name, final AlertDialog dialog) {
        SharedPreferences preferences = getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        String userId = Integer.toString(preferences.getInt(Constants.user_id, 0));

        AttributeAddInput attributeAddInput = new AttributeAddInput(userId, cat_id, attribute_name, attribute_type_string);
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();

        Call<AttributeAddOutput> call = apiService.attribute_add(attributeAddInput);

        call.enqueue(new Callback<AttributeAddOutput>() {
            @Override
            public void onResponse(Call<AttributeAddOutput> call, retrofit2.Response<AttributeAddOutput> response) {

                hud.dismiss();
                if (response.code()==200) {
                    if(response.body().getCode().equals("200")) {
                        AttributeAddOutput attributeAddOutput = response.body();
                        System.out.println(new Gson().toJson(attributeAddOutput));


                        dialog.dismiss();
                        viewattributeSet(cat_id);
                    }
                }
            }

            @Override
            public void onFailure(Call<AttributeAddOutput> call, Throwable t) {
                Toast.makeText(AttributeSetsPage.this,"Something went wrong..Try again after sometime",Toast.LENGTH_SHORT).show();

                hud.dismiss();
            }
        });
    }


}
