package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AttributeAddInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.OptionAddInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.OptionOutput.OptionAddOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_output.AttributeAddOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributesItem;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.OptionsListItem;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.AdditionalAttributePage;
import com.shopping.techxform.amersouq.activities.Payment.PaymentViewPage;
import com.shopping.techxform.amersouq.activities.attribure_add.AttributeSetsPage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by techxform on 10-Mar-19.
 */


public class ViewAttributesAdapter extends RecyclerView.Adapter<ViewAttributesAdapter.ViewHolder> {

    private List<AttributesItem> namelist = new ArrayList<>();


    Context context;
    String cat_id;

    Activity activity;

    public ViewAttributesAdapter(List<AttributesItem> namelist, Activity activity, Context context,String cat_id) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
        this.cat_id=cat_id;

    }

    public List<AttributesItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_view_list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setImageResource(namelist.get(position).getImg_drawable());
        holder.attribute_name_td.setText(namelist.get(position).getAttributename());
        if(namelist.get(position).getAttributetype().equals("DROPDOWN")){
            holder.add_option.setVisibility(View.VISIBLE);
            holder.root_layout.setVisibility(View.VISIBLE);
        }else{
            holder.add_option.setVisibility(View.GONE);
            holder.root_layout.setVisibility(View.GONE);
        }
        holder.attributes_type.setText(namelist.get(position).getAttributetype());


        LayoutInflater inflater = activity.getLayoutInflater();
        for(int i=0;i<namelist.get(position).getOptionsList().size();i++){
//            LinearLayout linearLayout=(LinearLayout)activity. getLayoutInflater().inflate(R.layout.option_text_lay, null);

            OptionsListItem listItem=namelist.get(position).getOptionsList().get(i);
            View view = inflater.inflate(R.layout.option_text_lay, null);
            TextView option_text=view.findViewById(R.id.option_td);
            option_text.setText(listItem.getOptionValue());

            holder.root_layout.addView(view);

        }
        holder.add_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.attribute_optionadd_layout, null);
                alertDialogBuilder.setView(view1);
                alertDialogBuilder.setCancelable(true);
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

                TextView attribute_td=dialog.findViewById(R.id.attribute_td);
                final EditText attribute_option_td=dialog.findViewById(R.id.attribute_option_td);
                Button submit_btn=dialog.findViewById(R.id.submit_btn);
                attribute_td.setText(namelist.get(position).getAttributename());

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(attribute_option_td.getText().toString().trim().equals("")){

                            Toast.makeText(activity,"Enter input value",Toast.LENGTH_SHORT).show();
                        }
                        else{
                        add_attribute(namelist.get(position).getAttributeId(),attribute_option_td.getText().toString(),dialog);
                    }}
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView add_option,attribute_name_td,attributes_type;
        LinearLayout root_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            root_layout = (LinearLayout) itemView.findViewById(R.id.root_layout);

            add_option = (TextView) itemView.findViewById(R.id.add_option);
            attribute_name_td = (TextView) itemView.findViewById(R.id.attribute_name_td);

            attributes_type = (TextView) itemView.findViewById(R.id.attributes_type);


        }

    }

    private void add_attribute(final String attr_id, String option, final AlertDialog dialog) {
        SharedPreferences preferences = activity.getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        String userId = Integer.toString(preferences.getInt(Constants.user_id, 0));

        OptionAddInput optionAddInput = new OptionAddInput(attr_id, option, userId);
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        final KProgressHUD hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();

        Call<OptionAddOutput> call = apiService.attribute_option_add(optionAddInput);

        call.enqueue(new Callback<OptionAddOutput>() {
            @Override
            public void onResponse(Call<OptionAddOutput> call, retrofit2.Response<OptionAddOutput> response) {

                hud.dismiss();
                if (response.code()==200) {
                    if(response.body().getCode().equals("200")) {
                        OptionAddOutput optionAddOutput1 = response.body();
                        System.out.println(new Gson().toJson(optionAddOutput1));


                        dialog.dismiss();
                        if(context instanceof AttributeSetsPage){
                            ((AttributeSetsPage)context).viewattributeSet(cat_id);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OptionAddOutput> call, Throwable t) {
                Toast.makeText(activity,"Something went wrong..Try again after sometime",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                hud.dismiss();
            }
        });
    }
}
