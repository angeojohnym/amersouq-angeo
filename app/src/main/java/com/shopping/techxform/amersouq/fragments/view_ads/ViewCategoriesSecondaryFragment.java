package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.CategoriesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.Status;
import com.shopping.techxform.amersouq.adapters.CategoriesSecondaryAdapter;
import com.shopping.techxform.amersouq.fragments.amer_souq_collections.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 07-Jan-19.
 */

public class ViewCategoriesSecondaryFragment extends Fragment {


    Activity activity;
    Bundle bundle;
    Context context;
    String cat_id = "";
    RecyclerView category_list;
    CategoriesSecondaryAdapter categoryAdapter;
    List<CategoryModel> category_listitems = new ArrayList<>();
    List<Integer> drawables = new ArrayList<>();
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_select_category, container, false);
        getwindowsproperty();
        activity = getActivity();
        context = getActivity();
        category_list = view.findViewById(R.id.cat_list);


        bundle = getArguments();

        cat_id = bundle.getString("cat_id");
        set_categories(cat_id);


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
                            categoryAdapter = new CategoriesSecondaryAdapter(allCategories, activity, context);

                            category_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            category_list.setItemAnimator(new DefaultItemAnimator());
                            category_list.setNestedScrollingEnabled(false);
                            category_list.setAdapter(categoryAdapter);


                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<CategoriesOutput> call, Throwable t) {


            }
        });
    }


}

