package com.shopping.techxform.amersouq.activities.Intro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData.SearchAd;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewFullAd;

import java.util.ArrayList;

/**
 * Created by mancj on 27.01.17.
 */

public class CustomSuggestionsAdapter extends SuggestionsAdapter<SearchAd, CustomSuggestionsAdapter.SuggestionHolder> {

    Activity activity;
    public CustomSuggestionsAdapter(LayoutInflater inflater, Activity activity) {
        super(inflater);
        this.activity=activity;
    }

    @Override
    public int getSingleViewHeight() {
        return 80;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.search_list_item, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(final SearchAd suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion.getAdName());
//        holder.subtitle.setText("The price is " + suggestion.getPrice() + "$");

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("clicked..",suggestion.getAdName());
                Intent intent=new Intent(activity, ViewFullAd.class);
                intent.putExtra("ad_type_id",suggestion.getAdTypeId());
                intent.putExtra("ad_id",suggestion.getAdId());
                intent.putExtra("type","all");

                activity.startActivity(intent);
            }
        });
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if(term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<>();
                    for (SearchAd item: suggestions_clone)
                        if(item.getAdName().toLowerCase().contains(term.toLowerCase()))
                            suggestions.add(item);
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<SearchAd>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView subtitle;
        protected ImageView image;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}
