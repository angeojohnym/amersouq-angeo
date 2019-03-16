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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shopping.techxform.amersouq.FeedbackChatActivity;
import com.shopping.techxform.amersouq.Models.AdView;
import com.shopping.techxform.amersouq.Models.FeedbackResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.adapters.FeedbackAdapter;
import com.shopping.techxform.amersouq.adapters.ViewAddAdapter;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ViewAddsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar mToolbar;
    private ArrayList<AdView> mViewAddList;
    private RecyclerView mAdRv;
    private ViewAddAdapter mAdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_adds);
        getwindowsproperty();
        mContext=this;
        init();
    }

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    private void init() {
        mViewAddList=new ArrayList<>();
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        mAdRv=findViewById(R.id.feedback_recycler);
        mAdRv.setLayoutManager(manager);
        mAdRv.setHasFixedSize(true);

        mAdAdapter=new ViewAddAdapter(mContext, mViewAddList, new RecyclerClick() {
            @Override
            public void onClickItem(View view, int position) {
            }
        });
        mAdRv.setAdapter(mAdAdapter);
        addData();
    }

    private void addData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid=sharedPreferences.getInt(Constants.user_id,0);
        Call<JsonObject> call = apiService.viewAdds(uid,0);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                JsonObject feedbackResponse=response.body();
                if (feedbackResponse!=null){
                    mViewAddList.clear();
                    System.out.println("RESULT"+feedbackResponse.toString());
//                    try {
//                        JSONArray array=feedbackResponse.getJSONArray("package");
//                        for(int i = 0; i < array.length(); i++)
//                        {
//                            JSONObject objects = array.getJSONObject(i);
//                            String title=objects.getString("title");
//                            String description=objects.getString("short_description");
//                            String image=objects.getString("image");
//                            mViewAddList.add(new AdView(title,description,image));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                    mAdAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(ViewAddsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}