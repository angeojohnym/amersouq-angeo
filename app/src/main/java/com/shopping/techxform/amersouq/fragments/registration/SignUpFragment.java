package com.shopping.techxform.amersouq.fragments.registration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.SignUpInput;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.fragments.ad_creation.CreationAdFragment;

import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 20-Nov-18.
 */


public class SignUpFragment extends Fragment {
    TextView submit_btn,signup_otp;
    String code_Picker;
    EditText input_name,input_email,input_mobile,input_password,input_con_pass;
    CountryCodePicker ccp;
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_sign_up_page, container, false);
        getwindowsproperty();
        submit_btn=view.findViewById(R.id.submit_btn);
        signup_otp=view.findViewById(R.id.signup_otp);
        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);
        input_name=view.findViewById(R.id.input_name);
        input_email=view.findViewById(R.id.input_email);
        input_mobile=view.findViewById(R.id.input_mobile);
        input_password=view.findViewById(R.id.input_password);
        input_con_pass=view.findViewById(R.id.input_conpassword);

        ccp.showFullName(false);
        ccp.showNameCode(false);
        ccp.setDefaultCountryUsingNameCode("IN");
        ccp.resetToDefaultCountry();




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=input_name.getText().toString().trim();
                String email=input_email.getText().toString().trim();
                String mobile=input_mobile.getText().toString().trim();
                String password=input_password.getText().toString().trim();
                ccp.registerCarrierNumberEditText(input_mobile);
                 code_Picker = ccp.getFullNumberWithPlus();

                if(name.equals("")||email.equals("")||mobile.equals("")||password.equals("")){

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Data Incomplete")
                            .setContentText("Fill all the fields")
                            .show();

                }else if(mobile.length()<10||mobile.length()>13){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("Enter a valid mobile number")
                            .show();

                }

                else {
//                    signup_function(name,mobile,password/*,email*/);
                    //facebook accoounts kit

                    signup_function(name,mobile,password,email);

//                    creationAdFragment.setArguments(bundle);
//
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.fragment_container, creationAdFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();


                }

            }
        });


        signup_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FaceBookAuthentication creationAdFragment = new FaceBookAuthentication();
                Intent in=new Intent(getActivity(),FaceBookAuthentication.class);
                Bundle bundle = new Bundle();
                bundle.putString("userPhone", input_mobile.getText().toString());
                bundle.putString("userPhone_with_code", code_Picker);
                bundle.putString("name", input_name.getText().toString());
                bundle.putString("password", input_password.getText().toString());
                bundle.putString("confirm_password", input_con_pass .getText().toString());
                in.putExtras(bundle);
                getActivity().startActivity(in);
            }
        });

        return view;


    }



    public void signup_function(final String name, final String contact, final String password
, String email
) {

        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        SignUpInput signUpInput=new SignUpInput(name,name,contact, password,email,email,"android","token");

        Call<ResponseBody> call = apiService.signup_service(signUpInput);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);

                if (response.code() == 200) {
                    ResponseBody responseBody=response.body();

                    try {
                        JSONObject json = new JSONObject(responseBody.string());

                        Boolean status_response=json.getBoolean("status");

                        if(status_response){
                            /*Intent intent=new Intent(getActivity(), HomePage.class);
                            startActivity(intent);*/
                            FaceBookAuthentication creationAdFragment = new FaceBookAuthentication();
                            Intent in=new Intent(getActivity(),FaceBookAuthentication.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("userPhone", contact);
                            bundle.putString("userPhone_with_code", code_Picker);
                            bundle.putString("name", name);
                            bundle.putString("password", password);
                            in.putExtras(bundle);
                            getActivity().startActivity(in);
                        }
                        else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Invalid")
                                    .setContentText("Invalid entries in the field")
                                    .show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Invalid")
                                .setContentText("Invalid entries in the field")
                                .show();
                    }


                } else {
                    ResponseBody responseBody1=response.errorBody();
                    try {
                        JSONObject json = new JSONObject(responseBody1.string());
                        JSONObject error_msg=json.getJSONObject("error");
                        String error_txt=error_msg.getString("message");
                        Toast.makeText(getActivity(),error_txt,Toast.LENGTH_SHORT).show();

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Invalid")
                                .setContentText(error_txt)
                                .show();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("Something went wrong..Try again after sometime")
                            .show();
                }


                hud.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("No Connection")
                        .setContentText("Check the internet connection")
                        .show();
                hud.dismiss();

            }
        });
    }


}