package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.Models.Packages;
import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;

public class PackagePagerAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private ArrayList<Packages> mResources;

    public PackagePagerAdapter(Context context, ArrayList<Packages> resources) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = resources;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_package_adapter,container,false);
//        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
//        Glide.with(mContext).load(mResources.get(position).getImageUrl())
//                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}