package com.shopping.techxform.amersouq.fragments.registration;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.Intro.PackagesActivity;

/**
 * Created by techxform on 20-Nov-18.
 */

public class EnterOtpFragment extends Fragment {
    Button submit_otp;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_enter_otp, container, false);
        getwindowsproperty();
        submit_otp=view.findViewById(R.id.submit_otp);

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PackagesActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;

    }

}