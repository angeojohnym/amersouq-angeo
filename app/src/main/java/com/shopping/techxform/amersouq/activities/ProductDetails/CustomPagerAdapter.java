package com.shopping.techxform.amersouq.activities.ProductDetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shopping.techxform.amersouq.R;

public class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private int[] mResources;

    public CustomPagerAdapter(Context context, int[] resources) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = resources;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.pager_item,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}