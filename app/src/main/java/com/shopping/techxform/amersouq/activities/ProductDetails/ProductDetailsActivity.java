package com.shopping.techxform.amersouq.activities.ProductDetails;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.CategoryIconModel;
import com.shopping.techxform.amersouq.adapters.AlertsAdapter;
import com.shopping.techxform.amersouq.adapters.CategoryHomeIconAdapter;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    int[] mResources = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, R.drawable.img_d,
            R.drawable.img_e, R.drawable.img_a};

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    ArrayList<CategoryIconModel> categoryIconModels = new ArrayList<>();
    AlertsAdapter alertsAdapter;
    RecyclerView featured_list, ascollection_list, categories_list, auctions_list;
    List<String> featured_listitems = new ArrayList<>();
    List<String> auction_listitems = new ArrayList<>();

    List<String> ascollection_listitems = new ArrayList<>();
    HomeAdapter featuredAdapter, ascollectionAdapter, categoryAdapter, auctionAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getwindowsproperty();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);


//        SpannableString spannable = new SpannableString(text);
//        spannable.setSpan(new StrikethroughSpan(), 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        remoteViews.setTextViewText(R.id.itemText, spannable);
        TextView someTextView = (TextView) findViewById(R.id.et_amount);
        someTextView.setText("$1000");
        someTextView.setPaintFlags(someTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        setPageViewIndicator();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        featured_list = (RecyclerView) findViewById(R.id.featured_ads_td_layout);
        ascollection_list = (RecyclerView) findViewById(R.id.as_collections_layout);

        for (int i = 0; i < 16; i++) {

            featured_listitems.add("abc");
            ascollection_listitems.add("fgh");
        }

        for (int j = 0; j < 4; j++) {

            auction_listitems.add("abc");
        }

        featuredAdapter = new HomeAdapter(featured_listitems, this, R.layout.featured_ad_listitem, this, 1);
        featured_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featured_list.setItemAnimator(new DefaultItemAnimator());
        featured_list.setNestedScrollingEnabled(false);
        featured_list.setAdapter(featuredAdapter);

        ascollectionAdapter = new HomeAdapter(ascollection_listitems, this, R.layout.as_collection_item, this, 1);
        ascollection_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ascollection_list.setItemAnimator(new DefaultItemAnimator());
        ascollection_list.setNestedScrollingEnabled(false);
        ascollection_list.setAdapter(ascollectionAdapter);
    }

    private void setPageViewIndicator() {

//        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            params.setMarginStart(4);
            params.setMarginEnd(4);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    mViewPager.setCurrentItem(presentPosition);
                    return false;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
