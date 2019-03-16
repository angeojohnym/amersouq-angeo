package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.shopping.techxform.amersouq.Models.ProfileModel;
import com.shopping.techxform.amersouq.Models.UserListModel;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.AddProduct.AddProductActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>  {

    private Context mContext;
    private List<UserListModel> profileModels;

    public UserListAdapter(Context mContext, List<UserListModel> profileModels) {
        this.mContext = mContext;
        this.profileModels = profileModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_user_list, parent, false);

        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivItemImage.setBackground(mContext.getDrawable(profileModels.get(position).getImage()));
        holder.txtItemName.setText(profileModels.get(position).getName());
        holder.txtDescription.setText(profileModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtItemName , txtDescription;
        public ImageView ivItemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtItemName = itemView.findViewById(R.id.tv_name);
            txtDescription = itemView.findViewById(R.id.tv_description);
            ivItemImage = (CircularImageView)itemView.findViewById(R.id.iv_profile_img);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, AddProductActivity.class);
//                    mContext.startActivity(intent);
//                }
//            });

        }
        @Override
        public void onClick(View view) {

        }
    }

    public void notifyBtnClick(List<UserListModel> profileModel){
        this.profileModels.clear();
        this.profileModels.addAll(profileModel);
        notifyDataSetChanged();
    }
}
