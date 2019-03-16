package com.shopping.techxform.amersouq.fragments.ad_creation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.AllLanguage;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.LanguagesOutput;
import com.shopping.techxform.amersouq.Utils.CreateAdTransferModel;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 27-Nov-18.
 */


public class CreationAdFragment extends Fragment  {
    private static final int auct_int = 1;
    private static final int classified_int = 2;
    private static final int fixed_price_int = 3;
    Activity activity;
    Context context;
    TextView next_button,visible_td;
    String lan_id = "0",main_cat="",Cat_id="", Category_Type ="",ad_id;
    String language = "";
    EditText input_adtitle,input_addesc,input_fulldesc;
    int currrent_type = 0,visible_state=1;
    MaterialSpinner language_spinner;
    RecyclerView image_list;
    AllCategory category_selected = null;
    HomeAdapter ascollectionAdapter;
    RadioButton auct_btn, classified_btn, fixed_price_btn;
    List<String> ascollection_listitems = new ArrayList<>();
    SegmentedGroup rg;
    CheckBox boost_ad_checkbox,visible_checkbox,featuredcheck;
    Calendar myCalendar ;
    String Contactinfo="",Best_Price="",Reserved_Price="",Start_From="",short_desc_td,full_desc_td,title,ClassifiedType,offerPrice,Address,Price;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_ad_first, container, false);
        getwindowsproperty();
        image_list = view.findViewById(R.id.image_list);
        next_button = view.findViewById(R.id.nxt_btn);
        auct_btn = view.findViewById(R.id.auct_btn);
        fixed_price_btn = view.findViewById(R.id.fixed_btn);
        classified_btn = view.findViewById(R.id.classified_btn);
        language_spinner = view.findViewById(R.id.language_spinner);
        rg = (SegmentedGroup) view.findViewById(R.id.segmented2);
        visible_checkbox=view.findViewById(R.id.visible_checkbox);
        visible_td=view.findViewById(R.id.visible_td);

        input_adtitle=(EditText)view.findViewById(R.id.input_adtitle);
        input_addesc=(EditText)view.findViewById(R.id.input_addesc);
        input_fulldesc=(EditText)view.findViewById(R.id.input_fulldesc);
        myCalendar = Calendar.getInstance();


        boost_ad_checkbox = (CheckBox) view.findViewById(R.id.boost_ad_checkbox);

        Bundle bundle = getArguments();
        Category_Type =bundle.getString("Cat");

        System.out.println("SHANIL KICHU 1 :::  "+Category_Type);


            Cat_id=bundle.getString("Cat_id");
        ad_id=bundle.getString("ad_id");
        category_selected = (AllCategory) bundle.getSerializable("category_selected");
        main_cat=bundle.getString("main_cat");


        if (Category_Type.equals("3")){
            Contactinfo=bundle.getString("Contactinfo");
            Best_Price=bundle.getString("Best_Price");
            Reserved_Price=bundle.getString("Reserved_Price");
            Start_From=bundle.getString("Start_From");
            short_desc_td=bundle.getString("short_desc_td");
            full_desc_td=bundle.getString("full_desc_td");
            title=bundle.getString("title");
            currrent_type=auct_int;
            rg.setVisibility(View.GONE);
        }else if (Category_Type.equals("1")){
            rg.setVisibility(View.GONE);
            Contactinfo=bundle.getString("Contactinfo");
            Best_Price=bundle.getString("Best_Price");
            Reserved_Price=bundle.getString("Reserved_Price");
            Start_From=bundle.getString("Start_From");
            short_desc_td=bundle.getString("short_desc_td");
            full_desc_td=bundle.getString("full_desc_td");
            offerPrice=bundle.getString("offerPrice");
            Price=bundle.getString("Price");
            title=bundle.getString("title");
            ClassifiedType=bundle.getString("ClassifiedType");
            currrent_type=classified_int;
        }else if (Category_Type.equals("2")){
            rg.setVisibility(View.GONE);
            Contactinfo=bundle.getString("Contactinfo");
            Best_Price=bundle.getString("Best_Price");
            Reserved_Price=bundle.getString("Reserved_Price");
            Start_From=bundle.getString("Start_From");
            short_desc_td=bundle.getString("short_desc_td");
            full_desc_td=bundle.getString("full_desc_td");
            offerPrice=bundle.getString("offerPrice");
            Price=bundle.getString("Price");
            title=bundle.getString("title");
            ClassifiedType=bundle.getString("ClassifiedType");
            currrent_type=fixed_price_int;
        }else if (Category_Type.equals("0")){




        }



        activity = getActivity();
        context = getActivity();


//        for(int i=0;i<10;i++){
//
//            ascollection_listitems.add("fgh");
//        }
//
//        ascollectionAdapter=new HomeAdapter(ascollection_listitems,activity,R.layout.image_item,context,0);
//        image_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
//        image_list.setItemAnimator(new DefaultItemAnimator());
//        image_list.setNestedScrollingEnabled(false);
//        image_list.setAdapter(ascollectionAdapter);

        set_languages(language_spinner);

//            visible_td.setText("Available");

        int num_of_types=0;

        if (category_selected.getIsAuction().equals("0")) {
            auct_btn.setVisibility(View.GONE);
        }else
        {
            num_of_types++;
        }
        if (category_selected.getIsFixedprice().equals("0")) {
            fixed_price_btn.setVisibility(View.GONE);

        }
        else
        {
            num_of_types++;
        }
        if (category_selected.getIsClassified().equals("0")) {
            classified_btn.setVisibility(View.GONE);

        }
        else
        {
            num_of_types++;
        }
//        Toast.makeText(getActivity(),Integer.toString(num_of_types),Toast.LENGTH_SHORT).show();

        if(num_of_types==1){
            auct_btn.setVisibility(View.GONE);
            fixed_price_btn.setVisibility(View.GONE);
            classified_btn.setVisibility(View.GONE);
            if (category_selected.getIsAuction().equals("1")) {
                currrent_type = auct_int;

            }
            else if (category_selected.getIsFixedprice().equals("1")) {
                currrent_type = fixed_price_int;

            }
            else if (category_selected.getIsClassified().equals("1")) {

                currrent_type = classified_int;
            }
            else
            {
                Toast.makeText(getActivity(),"No adtypes Specified",Toast.LENGTH_SHORT).show();
            }

        }


        input_adtitle.setText(title);
        input_addesc.setText(short_desc_td);
        input_fulldesc.setText(full_desc_td);


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAdTransferModel createAdTransferModel = new CreateAdTransferModel(ad_id,category_selected.getCategoryId(),
                        "EN",input_adtitle.getText().toString(),input_addesc.getText().toString(),
                        input_fulldesc.getText().toString(),main_cat,visible_state,Contactinfo,Best_Price,Reserved_Price,Start_From,Category_Type,offerPrice,Address,Price,ClassifiedType);
                Bundle bundle = new Bundle();
                bundle.putSerializable("transfer_data", createAdTransferModel);
                if (currrent_type == classified_int) {

//                    CreationAdFragment creationAdFragment = new CreationAdFragment();
                  // Put anything what you want
//                    creationAdFragment.setArguments(bundle);

                    ClassifiedFragment classifiedFragment = new ClassifiedFragment();
                    classifiedFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations()

                    fragmentTransaction.replace(R.id.fragment_container, classifiedFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (currrent_type == auct_int) {
                    AuctionFragment auctionFragment = new AuctionFragment();
                    auctionFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations()
                    fragmentTransaction.replace(R.id.fragment_container, auctionFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (currrent_type == fixed_price_int) {
                    FixedPriceFragment fixedPriceFragment = new FixedPriceFragment();
                    fixedPriceFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations()
                    fragmentTransaction.replace(R.id.fragment_container, fixedPriceFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Select")
                            .setContentText("Kindly select the advertisement type")
                            .show();
                }
            }
        });


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.auct_btn:
                        // do operations specific to this selection
                        currrent_type = auct_int;
                        break;
                    case R.id.fixed_btn:
                        // do operations specific to this selection
                        currrent_type = fixed_price_int;

                        break;
                    case R.id.classified_btn:
                        // do operations specific to this selection
                        currrent_type = classified_int;

                        break;
                }
            }
        });


//        visible_checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (((CheckBox) view).isChecked()) {
//
//                    visible_state=1;
//
//                }else
//                {
//                    visible_state=0;
//                }
//                }
//
//        });

        boost_ad_checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    //Case 1

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    LayoutInflater inflater = (LayoutInflater) context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view1 = inflater.inflate(R.layout.boost_ad_layout, null);
                    alertDialogBuilder.setView(view1);
                    alertDialogBuilder.setCancelable(true);
                    final AlertDialog dialog = alertDialogBuilder.create();
                    dialog.show();
                    final EditText from_date,from_time,to_date,to_time;
                    TextView nxt_btn;
                    from_date=(EditText)dialog.findViewById(R.id.from_date);
                    to_date=(EditText)dialog.findViewById(R.id.to_date);
                    from_time=(EditText)dialog.findViewById(R.id.from_time);
                    to_time=(EditText)dialog.findViewById(R.id.to_time);
                    nxt_btn=(TextView)dialog.findViewById(R.id.nxt_btn);

                    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            // TODO Auto-generated method stub
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, monthOfYear);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabel(from_date);
                        }

                    };
                    final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            // TODO Auto-generated method stub
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, monthOfYear);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabel(to_date);
                        }

                    };



                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    final TimePickerDialog from_time_picker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            from_time.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time

                    final TimePickerDialog to_time_picker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            to_time.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time






                    from_date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new DatePickerDialog(getActivity(), date, myCalendar
                                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    });
                    to_date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new DatePickerDialog(getActivity(), date1, myCalendar
                                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    });
                    from_time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            from_time_picker.setTitle("Select Time");
                            from_time_picker.show();
                        }
                    });
                    to_time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            to_time_picker.setTitle("Select Time");
                            to_time_picker.show();
                        }
                    });

                    nxt_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           dialog.dismiss();
                        }
                    });
                }

            }
        });







        return view;

    }

    public void set_languages(final MaterialSpinner lan_spinner) {

        final ArrayList<SpinnerModel> lan_list = new ArrayList<>();
        lan_list.add(new SpinnerModel("0", "Select Language"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<LanguagesOutput> call = apiService.get_languages();

        call.enqueue(new Callback<LanguagesOutput>() {
            @Override
            public void onResponse(Call<LanguagesOutput> call, retrofit2.Response<LanguagesOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    LanguagesOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<AllLanguage> allLanguages = new ArrayList<>();
                            allLanguages = responseBody.getAllLanguages();
                            for (int i = 0; i < allLanguages.size(); i++) {
                                lan_list.add(new SpinnerModel(allLanguages.get(i).getLanguageId(), allLanguages.get(i).getLanguage()));
                            }

                            lan_spinner.setItems(lan_list);
                            lan_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                    SpinnerModel spinnerModel = (SpinnerModel) item;
                                    lan_id = spinnerModel.getId();
                                    language = spinnerModel.getName();
                                }

                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<LanguagesOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    private void updateLabel(EditText editText) {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }


}
