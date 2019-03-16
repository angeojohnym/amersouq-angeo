package com.shopping.techxform.amersouq.fragments.registration;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Home.HomePage;
import com.shopping.techxform.amersouq.activities.Intro.SignInPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by techxform on 14-Mar-19.
 */

public class FaceBookAuthentication extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 99;
    private final Map<Integer, OnCompleteListener> permissionsListeners = new HashMap<>();
    String NewUser, phone, userID, userPhoneNumber,  QuizFlag, type,userPhone_with_code,userPhone,name,password;

    UIManager uiManager;
    private int nextPermissionsRequestCode = 4000;
    private SkinManager.Skin skin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        AccessToken accessToken = AccountKit.getCurrentAccessToken();
//
//        if (accessToken != null) {
//            //Handle Returning User
//
//            System.out.println("SHANIL KICHU   TEST  :::: ");
//        } else {
//            //Handle new or logged out user}
//
//            System.out.println("SHANIL KICHU   TEST2:::: ");
//        }

        Bundle b = getIntent().getExtras();
        userPhone_with_code = b.getString("userPhone_with_code");
        userPhone = b.getString("userPhone");
        name = b.getString("name");
        password = b.getString("password");

        System.out.println("SHANIL KICHU   1:::: "+userPhone_with_code);
        System.out.println("SHANIL KICHU   2:::: "+userPhone);
        System.out.println("SHANIL KICHU   3:::: "+name);
        System.out.println("SHANIL KICHU   4:::: "+password);





        onLogin(LoginType.PHONE);
    }

//    @Nullable
//    @Override
//    public View onView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//
//
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//
//    }


    private void onLogin(final LoginType loginType) {

        System.out.println("SHANIL KICHU   10:::: "+userPhone);
        final Intent intent = new Intent(getApplicationContext(), AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);

        configurationBuilder.setFacebookNotificationsEnabled(true);
        configurationBuilder.setDefaultCountryCode("IN");
        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);

        PhoneNumber phone = new PhoneNumber("+91", userPhone, "IN");
        configurationBuilder.setInitialPhoneNumber(phone);


        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);

        uiManager = new SkinManager(
                SkinManager.Skin.TRANSLUCENT,
                getResources().getColor(R.color.black));


        configurationBuilder.setUIManager(uiManager);


        OnCompleteListener completeListener = new OnCompleteListener() {
            @Override
            public void onComplete() {

                startActivityForResult(intent, APP_REQUEST_CODE);

            }
        };

        completeListener.onComplete();


    }


    @TargetApi(23)
    @SuppressWarnings("unused")
    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           final @NonNull String permissions[],
                                           final @NonNull int[] grantResults) {
        final OnCompleteListener permissionsListener = permissionsListeners.remove(requestCode);
        if (permissionsListener != null
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionsListener.onComplete();
        }
    }

    @Override
    public void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        System.out.println("SHANIL KICHU   11:::: ");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                System.out.println("SHANIL KICHU   5:::: "+toastMessage);
                FaceBookAuthentication.this.finish();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
                System.out.println("SHANIL KICHU   6:::: "+toastMessage);
                FaceBookAuthentication.this.finish();
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    System.out.println("SHANIL KICHU   7:::: "+toastMessage);

                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0, 10));
                    System.out.println("SHANIL KICHU   8:::: "+toastMessage);
                }

                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                    @Override
                    public void onSuccess(final Account account) {
                        String accountKitId = account.getId();
                        PhoneNumber phoneNumber = account.getPhoneNumber();
                        String CountryCode = phoneNumber.getCountryCode();

                        String phoneNumberString = phoneNumber.toString();
                        System.out.println("SHANIL KICHU   9:::: "+phoneNumberString);

                        if (phoneNumberString.equals("+" + CountryCode + userPhone)) {


//                            if (NewUser.equals("true")) {
//
//                                FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(FaceBookAuthentication.this);
//                                Bundle bundle = new Bundle();
//                                bundle.putString("User_type", "new_user");
//                                mFirebaseAnalytics.logEvent("Authentication_success", bundle);
//                                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//                                Intent i = new Intent(FaceBookAuthentication.this, SignInPage.class);
//                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                Bundle b = new Bundle();
//                                b.putString("userPhone", phone);
//                                i.putExtras(b);
//                                startActivity(i);
//                                FaceBookAuthentication.this.finish();
//
//
//                            } else {
                                FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(FaceBookAuthentication.this);
                                Bundle bundle = new Bundle();
                                bundle.putString("User_type", "existing_user");
                                bundle.putString("User_Number", userPhoneNumber);
                                mFirebaseAnalytics.logEvent("Authentication_success", bundle);
                                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                                SharedPreferences.Editor editor = FaceBookAuthentication.this.getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                                editor.putString("userID", userID);
                                editor.putString("userPhoneNumber", userPhoneNumber);
                                editor.putString("QuizFlag", "true");
                                editor.putString("NewUpdate", "5");
                                editor.commit();

                            SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences.edit();
                            editor1.putBoolean(Constants.login_status, true);
                            editor1.apply();
                                Intent in = new Intent(FaceBookAuthentication.this, HomePage.class);
                                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                Bundle bn = new Bundle();
                                bn.putString("Type", "CwType");
                                in.putExtras(bn);
                                startActivity(in);
                                FaceBookAuthentication.this.finish();


//                            }
                        } else {


                            Toast.makeText(FaceBookAuthentication.this, "Number Mismatch Error !", Toast.LENGTH_SHORT).show();
                            FaceBookAuthentication.this.finish();
                        }


                    }

                    @Override
                    public void onError(final AccountKitError error) {
                        // Handle Error

                        Toast.makeText(FaceBookAuthentication.this, "Authentication Error !", Toast.LENGTH_SHORT).show();

                        FaceBookAuthentication.this.finish();

                    }
                });


            }

        }
    }

    private interface OnCompleteListener {
        void onComplete();
    }


}



