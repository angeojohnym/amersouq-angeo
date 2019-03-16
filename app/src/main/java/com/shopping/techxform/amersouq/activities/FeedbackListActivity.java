package com.shopping.techxform.amersouq.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shopping.techxform.amersouq.FeedbackChatActivity;
import com.shopping.techxform.amersouq.Models.AlertResponse;
import com.shopping.techxform.amersouq.Models.Feedback;
import com.shopping.techxform.amersouq.Models.FeedbackResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;
import com.shopping.techxform.amersouq.adapters.FeedbackAdapter;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class FeedbackListActivity extends BaseActivity {

    private RecyclerView mFeedbackRv;
    private Context mContext;
    private ArrayList<Feedback> mFeedbackArrayList;
    private Toolbar mToolbar;
    private FeedbackAdapter mFeedbackAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        getwindowsproperty();
        mContext=this;
        init();
    }

    private void init() {
        mFeedbackArrayList=new ArrayList<>();
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        mFeedbackRv=findViewById(R.id.feedback_recycler);
        mFeedbackRv.setLayoutManager(manager);
        mFeedbackRv.setHasFixedSize(true);

        mFeedbackAdapter=new FeedbackAdapter(mContext, mFeedbackArrayList, new RecyclerClick() {
            @Override
            public void onClickItem(View view, int position) {
startActivity(new Intent(mContext,FeedbackChatActivity.class));
            }
        });
        mFeedbackRv.setAdapter(mFeedbackAdapter);
        addData();
    }

    private void addData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid=sharedPreferences.getInt(Constants.user_id,0);
        Call<FeedbackResponse> call = apiService.view_feedback(uid,0);

        call.enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, retrofit2.Response<FeedbackResponse> response) {
                FeedbackResponse feedbackResponse=response.body();
                System.out.println(new Gson().toJson(feedbackResponse));
                if (feedbackResponse!=null&&feedbackResponse.getFeedback()!=null&&feedbackResponse.getFeedback().size()>0){
                    mFeedbackArrayList.clear();
                    mFeedbackArrayList.addAll(feedbackResponse.getFeedback());
                    mFeedbackAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(FeedbackListActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {

                System.out.println("ERROR");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
