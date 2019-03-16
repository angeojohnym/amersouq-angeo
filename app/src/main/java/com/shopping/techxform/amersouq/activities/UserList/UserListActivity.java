package com.shopping.techxform.amersouq.activities.UserList;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shopping.techxform.amersouq.Models.ProfileModel;
import com.shopping.techxform.amersouq.Models.UserListModel;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.ProfileAdapter;
import com.shopping.techxform.amersouq.adapters.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    RecyclerView rvUserList;
    private List<UserListModel> userListModels;
    UserListAdapter userListAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getwindowsproperty();
        rvUserList = (RecyclerView) findViewById(R.id.rv_itemList);

        prepareUserList();
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(mLayoutManager);
        userListAdapter = new UserListAdapter(this,userListModels);
        rvUserList.setAdapter(userListAdapter);

    }

    private void prepareUserList() {
        userListModels = new ArrayList<>();
        UserListModel userListModel = new UserListModel("Add Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
        userListModel = new UserListModel("View Product", R.drawable.profile_icon , " It is usually written in a few sentences and phrases. Easy it may sound, however, when you set out to write it, you can possibly get overwhelmed.");
        userListModels.add(userListModel);
    }
}
