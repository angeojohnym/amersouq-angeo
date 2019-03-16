package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Payment.PaymentViewPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.ViewHolder> {

    private List<String> namelist = new ArrayList<>();


    Context context;

    private int rowLayout;

    Activity activity;

    public OrdersListAdapter(List<String> namelist, Activity activity, int rowLayout, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;


    }

    public List<String> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setImageResource(namelist.get(position).getImg_drawable());
//        holder.cat_text_name.setText(namelist.get(position).getTitle_category());

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, PaymentViewPage.class);
                activity.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        Button sub_btn;
        RelativeLayout full_layout;
//        LinearLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);

//            sub_btn = (Button) itemView.findViewById(R.id.sub_btn);
            full_layout = (RelativeLayout) itemView.findViewById(R.id.full_layout);

//            full_layout = (LinearLayout) itemView.findViewById(R.id.full_layout);


        }

    }
}
