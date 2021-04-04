package com.trainersstocks.CustomersApp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainersstocks.CustomersApp.activities.NavigationActivity;
import com.trainersstocks.CustomersApp.otpView.OnOtpComplete;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.otpView.PinView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OtpVarificationFragment extends Fragment implements OnOtpComplete {

    @BindView(R.id.firstPinView)
    PinView firstPinView;

    public static OtpVarificationFragment newInstance(String param1, String param2) {
        OtpVarificationFragment fragment = new OtpVarificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_varification, container, false);
        ButterKnife.bind(this, view);
        firstPinView.setOnOtpCompleteListner(this);

        return view;
    }


    @Override
    public void OnOtpComplete() {
        Intent intent = new Intent(getContext(), NavigationActivity.class);
        startActivity(intent);

    }
}