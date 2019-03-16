package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.techxform.amersouq.Models.CheckoutItem;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.AdditionalAttributePage;
import com.shopping.techxform.amersouq.activities.ProductsCartPage;

import java.util.ArrayList;
import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    Context context;
    int k = 0;
    Activity activity;
    private List<CheckoutItem> namelist = new ArrayList<>();
    private int rowLayout;

    public CheckoutAdapter(List<CheckoutItem> namelist, Activity activity, Context context, int k) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
        this.k = k;

    }

    public List<CheckoutItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());

        final CheckoutItem checkoutItem = namelist.get(position);
        holder.product_name.setText(checkoutItem.getTitle());
        holder.quantity_td.setText(Integer.toString(checkoutItem.getCount()));
        holder.item_total_price.setText(Double.toString(checkoutItem.getTotal_price()));
        holder.short_desc_td.setText(checkoutItem.getDesc());
        if (k == 1) {
            holder.bottom_layout.setVisibility(View.VISIBLE);
        } else {
            holder.bottom_layout.setVisibility(View.GONE);
        }

        holder.add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.quantity_td.getText().toString());
                qty = qty + 1;
                holder.quantity_td.setText(Integer.toString(qty));
                if(context instanceof ProductsCartPage){
                    ((ProductsCartPage)context).set_qty(checkoutItem.getAd_id(),Integer.parseInt(holder.quantity_td.getText().toString()));
                }
                holder.item_total_price.setText(Double.toString(qty*checkoutItem.getUnit_price()));
            }
        });
        holder.subtract_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.quantity_td.getText().toString());
                if (qty > 0) {
                    qty = qty - 1;
                    holder.quantity_td.setText(Integer.toString(qty));
                    if(context instanceof ProductsCartPage){
                        ((ProductsCartPage)context).set_qty(namelist.get(position).getAd_id(),Integer.parseInt(holder.quantity_td.getText().toString()));
                    }
                    holder.item_total_price.setText(Double.toString(qty*checkoutItem.getUnit_price()));


                }
            }
        });

        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof ProductsCartPage){
                    ((ProductsCartPage)context).delete_cart(namelist.get(position).getAd_id());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView product_name, short_desc_td, item_total_price, quantity_td;
        RelativeLayout full_layout, bottom_layout;
        ImageView add_img, subtract_img,delete_icon;


        public ViewHolder(View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            item_total_price = (TextView) itemView.findViewById(R.id.item_total_price);
            quantity_td = (TextView) itemView.findViewById(R.id.quantity_td);
            short_desc_td = (TextView) itemView.findViewById(R.id.short_desc_td);
            bottom_layout = (RelativeLayout) itemView.findViewById(R.id.bottom_layout);

            delete_icon = (ImageView) itemView.findViewById(R.id.delete_imgvw);

            add_img = (ImageView) itemView.findViewById(R.id.add_img);
            subtract_img = (ImageView) itemView.findViewById(R.id.subtract_img);
        }

    }
}
