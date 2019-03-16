package com.shopping.techxform.amersouq.activities;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.shopping.techxform.amersouq.Models.Alert;
import com.shopping.techxform.amersouq.Models.AlertResponse;
import com.shopping.techxform.amersouq.Models.Feedback;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;
import com.shopping.techxform.amersouq.adapters.FeedbackAdapter;
import com.shopping.techxform.amersouq.adapters.ProfileAlertAdapter;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AlertActivity extends BaseActivity {
    private RecyclerView mAlertRv;
    private Context mContext;
    private ArrayList<Alert> mAlertArrayList;
    private Toolbar mToolbar;
    private ProfileAlertAdapter mAlertAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        getwindowsproperty();

        mContext=this;
        init();
    }
    private void init() {
        mAlertArrayList=new ArrayList<>();
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        mAlertRv=findViewById(R.id.alert_recycler);
        mAlertRv.setLayoutManager(manager);
        mAlertRv.setHasFixedSize(true);

        mAlertAdapter=new ProfileAlertAdapter(mContext, mAlertArrayList, new RecyclerClick() {
            @Override
            public void onClickItem(View view, int position) {

            }
        });
        mAlertRv.setAdapter(mAlertAdapter);
        addData();
    }


    private void addData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<AlertResponse> call = apiService.view_alerts();

        call.enqueue(new Callback<AlertResponse>() {
            @Override
            public void onResponse(Call<AlertResponse> call, retrofit2.Response<AlertResponse> response) {
                AlertResponse productsResponse=response.body();
                if (productsResponse!=null&&productsResponse.getAlert()!=null&&productsResponse.getAlert().size()>0){
                    mAlertArrayList.clear();
                    mAlertArrayList.addAll(productsResponse.getAlert());
                    mAlertAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AlertResponse> call, Throwable t) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
