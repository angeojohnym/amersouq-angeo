package com.shopping.techxform.amersouq.fragments.registration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.LoginInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LoginOutputModule.LoginOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Home.HomePage;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 20-Nov-18.
 */


public class LoginFragment extends Fragment {
    TextView sign_up_td, sign_guest_btn,input_otp,input_forgetpass;
    EditText input_email, input_password;
    TextView login_btn;
    String code_Picker;
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

        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        getwindowsproperty();
        sign_up_td = view.findViewById(R.id.sign_up_td);
        input_otp = view.findViewById(R.id.input_otp);
        input_email = view.findViewById(R.id.input_mobile);
        input_password = view.findViewById(R.id.input_password);
        input_forgetpass = view.findViewById(R.id.forget_password);
        login_btn = view.findViewById(R.id.login_btn);
        sign_guest_btn = view.findViewById(R.id.sign_guest_btn);
        ccp=view.findViewById(R.id.ccp);
        ccp.showFullName(false);
        ccp.showNameCode(false);
        ccp.setDefaultCountryUsingNameCode("IN");
        ccp.resetToDefaultCountry();

        ccp.registerCarrierNumberEditText(input_email);
        code_Picker = ccp.getFullNumberWithPlus();

        sign_up_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final KProgressHUD hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .show();
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, signUpFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                hud.dismiss();
            }
        });

        input_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FaceBookAuthentication creationAdFragment = new FaceBookAuthentication();
                Intent in=new Intent(getActivity(),FaceBookAuthentication.class);
                Bundle bundle = new Bundle();
                bundle.putString("userPhone", input_email.getText().toString());
                bundle.putString("userPhone_with_code", code_Picker);
                bundle.putString("password", input_password.getText().toString());
                in.putExtras(bundle);
                getActivity().startActivity(in);

            }
        });

        sign_guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final KProgressHUD hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setMaxProgress(100)
                        .show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.login_status, true);
                editor.putInt(Constants.user_id, 0);
                editor.putString(Constants.first_name, "Guest");
                editor.apply();
                Intent intent = new Intent(getActivity(), HomePage.class);
                startActivity(intent);
                hud.dismiss();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_login(input_email.getText().toString().trim(), input_password.getText().toString().trim());
            }
        });

        return view;


    }


    public void check_login(String username, String password) {

        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        LoginInput loginInput = new LoginInput(username, password);

        Call<LoginOutput> call = apiService.login_service(loginInput);

        call.enqueue(new Callback<LoginOutput>() {
            @Override
            public void onResponse(Call<LoginOutput> call, retrofit2.Response<LoginOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    LoginOutput responseBody = response.body();

                    try {
//                        JSONObject json = new JSONObject(responseBody.string());

                        Boolean status_response = responseBody.getStatus();

                        if (status_response) {
                            Intent intent = new Intent(getActivity(), HomePage.class);
                            startActivity(intent);

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Constants.login_status, true);
                            editor.putInt(Constants.user_id, responseBody.getResult().getId());
                            editor.putString(Constants.image_url, responseBody.getResult().getProfilePic());

                            editor.putString(Constants.first_name, responseBody.getResult().getFirstName());
                            editor.putString(Constants.user_number, responseBody.getResult().getUser());
                            editor.putString(Constants.user_token, responseBody.getResult().getToken());
                            editor.apply();
                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Invalid")
                                    .setContentText("Username and password entered is not matching.")
                                    .show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Invalid")
                                .setContentText("Username and password entered is not matching.")
                                .show();
                    }


                } else {

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("Username and password entered is not matching.")
                            .show();
                }


                hud.dismiss();
            }

            @Override
            public void onFailure(Call<LoginOutput> call, Throwable t) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Server error")
                        .setContentText("Unable to contact the server")
                        .show();
                hud.dismiss();

            }
        });
    }


}