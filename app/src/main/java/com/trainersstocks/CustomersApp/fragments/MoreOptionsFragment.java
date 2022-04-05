package com.trainersstocks.CustomersApp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.activities.ChangePasswordMpinActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MoreOptionsFragment extends DialogFragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_options, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }


    @OnClick(R.id.rl_change_password)
    public void changePassword(){
        startActivity(new Intent(getContext(), ChangePasswordMpinActivity.class).putExtra("from","c_p"));
    }

    @OnClick(R.id.rl_change_mpin)
    public void changeMpin(){
        startActivity(new Intent(getContext(), ChangePasswordMpinActivity.class).putExtra("from","c_mpin"));
    }
}