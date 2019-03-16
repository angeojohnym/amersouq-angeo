package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.AllLanguage;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributesItem;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.OptionsListItem;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.activities.AddProductNew;
import com.shopping.techxform.amersouq.activities.AdditionalAttributePage;
import com.shopping.techxform.amersouq.fragments.ad_creation.ClassifiedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 04-Jan-19.
 */

public class AttributeSetAdapter extends RecyclerView.Adapter<AttributeSetAdapter.ViewHolder> {

    private List<AttributesItem> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;
    ClassifiedFragment fragment;
    Context context;

    Activity activity;

    public AttributeSetAdapter(List<AttributesItem> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;


    }

    public List<AttributesItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final AttributesItem model=namelist.get(position);

        holder.attribute_name_td.setText(model.getAttributename());
        if(model.getAttributetype().equals("DROPDOWN")){
            holder.dropdown.setVisibility(View.VISIBLE);

            List<OptionsListItem> allOptions = new ArrayList<>();
            ArrayList<SpinnerModel> option_list=new ArrayList<>();
            option_list.add(new SpinnerModel("0","Select"));
            allOptions = model.getOptionsList();
            for (int i = 0; i < allOptions.size(); i++) {
                option_list.add(new SpinnerModel(allOptions.get(i).getOptionId(), allOptions.get(i).getOptionValue()));
            }

            holder.dropdown.setItems(option_list);
            holder.dropdown.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                    SpinnerModel spinnerModel = (SpinnerModel) item;
                    String option = spinnerModel.getId();

                    if(context instanceof AdditionalAttributePage){
                        ((AdditionalAttributePage)context).set_option(model.getAttributeId(),option);
                    }

                }

            });
            holder.edit_input.setVisibility(View.GONE);
        }
        else  if(model.getAttributetype().equals("TEXT")){
            holder.dropdown.setVisibility(View.GONE);
            holder.edit_input.setVisibility(View.VISIBLE);
        }
        else  if(model.getAttributetype().equals("INTEGER")){
            holder.dropdown.setVisibility(View.GONE);
            holder.edit_input.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.dropdown.setVisibility(View.GONE);
            holder.edit_input.setVisibility(View.VISIBLE);
        }
        holder.edit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                if(context instanceof AdditionalAttributePage){
                    ((AdditionalAttributePage)context).set_option(model.getAttributeId(),s.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView attribute_name_td;
        EditText edit_input;
        MaterialSpinner dropdown;

        public ViewHolder(View itemView) {
            super(itemView);

            attribute_name_td = (TextView) itemView.findViewById(R.id.attribute_name_td);
            edit_input = (EditText) itemView.findViewById(R.id.edit_input);

            dropdown = (MaterialSpinner) itemView.findViewById(R.id.dropdown);


        }

    }
}
