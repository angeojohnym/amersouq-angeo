package com.shopping.techxform.amersouq.fragments.ad_creation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.CategoriesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.Status;
import com.shopping.techxform.amersouq.activities.AddProductNew;
import com.shopping.techxform.amersouq.activities.attribure_add.AttributeSetsPage;
import com.shopping.techxform.amersouq.adapters.CategoryDynamicAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 28-Dec-18.
 */


public class PostCategoryFragment extends Fragment {

    String flag = "";
    LinearLayout root_layout;
    TextView select_cat_btn;
    AllCategory current_category = null;
    Context context;
    Boolean cat_selected = false;
    PostCategoryFragment fragment1;
    int curr_flag = 1;
    String current_cat_id = "0";
    int max_flag = 1;
    String main_cat = "";
    Activity activity;
    CategoryDynamicAdapter categoryDynamicAdapter;
    RadioButton motors_radio, property_radio, jobs_radio, for_sale_radio, services_radio;

    public PostCategoryFragment newInstance(String flag) {

        Bundle args = new Bundle();

        args.putString("flag", flag);
        PostCategoryFragment fragment = new PostCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.select_category_layout, container, false);
        getwindowsproperty();
        root_layout = (LinearLayout) view.findViewById(R.id.root_layout);

        motors_radio = (RadioButton) view.findViewById(R.id.motors_radio);
        property_radio = (RadioButton) view.findViewById(R.id.property_radio);
        jobs_radio = (RadioButton) view.findViewById(R.id.jobs_radio);
        for_sale_radio = (RadioButton) view.findViewById(R.id.for_sale_radio);
        services_radio = (RadioButton) view.findViewById(R.id.services_radio);
        select_cat_btn = (TextView) view.findViewById(R.id.select_cat_btn);
        fragment1 = this;
        flag = getArguments().getString("flag");
        context = getActivity();
        activity = getActivity();


        motors_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                main_cat = motors_radio.getText().toString();
                set_categories("34");
            }
        });
        property_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_cat = property_radio.getText().toString();

                set_categories("35");
            }
        });
        jobs_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_cat = jobs_radio.getText().toString();

                set_categories("36");
            }
        });
        for_sale_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_cat = for_sale_radio.getText().toString();

                set_categories("37");
            }
        });
        services_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_cat = services_radio.getText().toString();

                set_categories("38");
            }
        });
        if (flag.equals("AdPost")) {
           motors_radio.setVisibility(View.VISIBLE);
            property_radio.setVisibility(View.VISIBLE);
            jobs_radio.setVisibility(View.VISIBLE);
            services_radio.setVisibility(View.VISIBLE);

        } else if (flag.equals("AddProduct")) {
            motors_radio.setVisibility(View.GONE);
            property_radio.setVisibility(View.GONE);
            jobs_radio.setVisibility(View.GONE);
            services_radio.setVisibility(View.GONE);
            for_sale_radio.setVisibility(View.GONE);
            set_categories("37");

        }
        else if (flag.equals("AddAttribute")) {
            motors_radio.setVisibility(View.GONE);
            property_radio.setVisibility(View.GONE);
            jobs_radio.setVisibility(View.GONE);
            services_radio.setVisibility(View.GONE);
            for_sale_radio.setVisibility(View.GONE);
            set_categories("37");

        }


        select_cat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (cat_selected) {

                    if (flag.equals("AdPost")) {
                        CreationAdFragment creationAdFragment = new CreationAdFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("category_selected", current_category);
                        bundle.putString("main_cat", main_cat);
                        bundle.putString("Cat", "0");
                        creationAdFragment.setArguments(bundle);

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, creationAdFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (flag.equals("AddProduct")) {

//                        Intent intent = new Intent(activity, AddProductActivity.class);
                        Intent intent = new Intent(activity, AddProductNew.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("category_selected", current_category);
                        bundle.putString("main_cat", main_cat);
                        bundle.putString("Cat", "0");
                        intent.putExtra("bundil", bundle);
                        activity.startActivity(intent);

                    }
                    else if (flag.equals("AddAttribute")) {

//                        Intent intent = new Intent(activity, AddProductActivity.class);
                        Intent intent = new Intent(activity, AttributeSetsPage.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("category_selected", current_category);
                        bundle.putString("main_cat", main_cat);
                        intent.putExtra("bundil", bundle);
                        bundle.putString("Cat", "0");
                        activity.startActivity(intent);

                    }


                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Not Selected")
                            .setContentText("Please select the sub category displayed")
                            .show();
                }
            }
        });


        return view;

    }

    public void set_categories(final String category) {

        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<CategoriesOutput> call = apiService.get_categories_ad(category);

        call.enqueue(new Callback<CategoriesOutput>() {
            @Override
            public void onResponse(Call<CategoriesOutput> call, retrofit2.Response<CategoriesOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    CategoriesOutput responseBody = response.body();

                    try {
//                        JSONObject json = new JSONObject(responseBody.string());
                        Status status = responseBody.getStatus();
                        String status_response = status.getCode();
                        if (status_response.equals("200")) {

                            List<AllCategory> allCategories = new ArrayList<>();
                            allCategories = responseBody.getAllCategories();
                            if (allCategories.size() > 0) {
                                set_new_catlist(category, "", 0, 0);
                            }

                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Invalid")
                                    .setContentText("No sub categories")
                                    .show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                } else {

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("No sub categories")
                            .show();
                }


                hud.dismiss();
            }

            @Override
            public void onFailure(Call<CategoriesOutput> call, Throwable t) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("No Connection")
                        .setContentText("Check the internet connection")
                        .show();
                hud.dismiss();

            }
        });
    }

    public void set_new_catlist(String category_id, final String cat_name, final int k, final int max_flag1) {

        current_cat_id = category_id;


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<CategoriesOutput> call = apiService.get_categories_ad(category_id);

        call.enqueue(new Callback<CategoriesOutput>() {
            @Override
            public void onResponse(Call<CategoriesOutput> call, retrofit2.Response<CategoriesOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    CategoriesOutput responseBody = response.body();

                    try {
//                        JSONObject json = new JSONObject(responseBody.string());
                        Status status = responseBody.getStatus();
                        String status_response = status.getCode();
                        if (status_response.equals("200")) {

//                            remove_extra(max_flag1);
                            int total_children = root_layout.getChildCount();
                            for (int i = max_flag1 + 1; i < total_children + 1; i++) {

                                try {
//                                    root_layout.removeViewAt(i);
                                    root_layout.removeView(root_layout.findViewById(i));
                                } catch (Exception e) {
                                    Log.e("no view", "not found 404");
                                }
                            }


                            List<AllCategory> allCategories = new ArrayList<>();
                            allCategories = responseBody.getAllCategories();
                            if (allCategories.size() > 0) {
                                cat_selected = false;


                                View layout2 = LayoutInflater.from(getActivity()).inflate(R.layout.child_category, root_layout, false);
                                if (k == 0) {
                                    root_layout.removeAllViews();
                                }
                                curr_flag = root_layout.getChildCount() + 1;

                                RecyclerView cat_list = (RecyclerView) layout2.findViewById(R.id.cat_list);
                                categoryDynamicAdapter = new CategoryDynamicAdapter(allCategories, activity, context, curr_flag, root_layout, fragment1);
                                cat_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                cat_list.setItemAnimator(new DefaultItemAnimator());
                                cat_list.setNestedScrollingEnabled(false);
                                cat_list.setAdapter(categoryDynamicAdapter);

                                layout2.setId(curr_flag);
                                root_layout.addView(layout2);


                            }

                        } else {
                            int total_children = root_layout.getChildCount();
                            for (int i = max_flag1 + 1; i < total_children + 1; i++) {

                                try {
//                                    root_layout.removeViewAt(i);
                                    root_layout.removeView(root_layout.findViewById(i));
                                } catch (Exception e) {
                                    Log.e("no view", "not found 404");
                                }
                            }
                            cat_selected = true;


                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Selected")
                                    .setContentText(cat_name)
                                    .show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                } else {

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText(cat_name)
                            .show();
                }


                hud.dismiss();
            }

            @Override
            public void onFailure(Call<CategoriesOutput> call, Throwable t) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("No Connection")
                        .setContentText("Check the internet connection")
                        .show();
                hud.dismiss();

            }
        });


    }

    public void all_features(AllCategory categoryitem) {
        current_category = categoryitem;

    }

//    public void remove_extra(int flag_current) {
//
//        for (int i = flag_current; i < root_layout.getChildCount()+1; i++) {
//
//            try {
//                root_layout.removeViewAt(i);
//            } catch (Exception e) {
//                Log.e("no view", "not found 404");
//            }
//        }
//
//    }


}
