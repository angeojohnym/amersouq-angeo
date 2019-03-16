package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.Models.cart.All_products;
import com.shopping.techxform.amersouq.Models.cart.CartSingleModel;
import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.AddCartViewHolder> {

    private int productCount = 0;
    private Context mContext;
    private ArrayList<CartSingleModel>mAll_products;

    public AddCartAdapter(Context mContext, ArrayList<CartSingleModel> mAll_products) {
        this.mContext = mContext;
        this.mAll_products = mAll_products;
    }

    //view product cart
    @NonNull
    @Override
    public AddCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_add_cart, viewGroup, false);

        return new AddCartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddCartViewHolder holder, final int position) {
final CartSingleModel all_products=mAll_products.get(position);
        holder.textViewCountMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity=Integer.parseInt(all_products.getQuantity());

                if (quantity>0){
                    quantity= quantity -= 1;
                    all_products.setQuantity(String.valueOf(quantity));
                    mAll_products.set(position,all_products);
                    holder.textViewCount.setText(String.valueOf(quantity));
                    notifyItemChanged(position);
                }
            }
        });

        holder.textViewCountPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(all_products.getQuantity());

                if (quantity>=0){
                    quantity= quantity += 1;
                    all_products.setQuantity(String.valueOf(quantity));
                    mAll_products.set(position,all_products);
                    holder.textViewCount.setText(String.valueOf(quantity));
                    notifyItemChanged(position);
                }
            }
        });
        holder.textViewOrginalPrice.setText(String.format("%s%s", "KD ", all_products.getPrice()));
        holder.textViewPrice.setText(String.format("%s%s", "KD ", all_products.getOfferPrice()));
        holder.textViewProductName.setText(all_products.getTitle());
        holder.textViewCount.setText(all_products.getQuantity());
        holder.sellerName.setText(all_products.getSeller());
        if (all_products.getImage()!=null){
            Glide.with(mContext)
                    .load(all_products.getImage())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mAll_products.size();
    }

    public class AddCartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCount ;
        ImageView textViewCountPlus,textViewCountMinus,imageView;
        TextView textViewProductName;
        TextView textViewPrice;
        TextView sellerName;
        TextView textViewOrginalPrice;
        public AddCartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewCountMinus = itemView.findViewById(R.id.textViewCountMinus);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            textViewCountPlus = itemView.findViewById(R.id.textViewCountPlus);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewOrginalPrice = itemView.findViewById(R.id.textViewOrginalPrice);
            sellerName = itemView.findViewById(R.id.textViewSeller);
        }
    }
}
