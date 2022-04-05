package com.trainersstocks.CustomersApp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.trainersstocks.CustomersApp.R;


public class OffersFragment extends DialogFragment {

   TabLayout tabLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        tabLayout = v.findViewById(R.id.tablayout);
        TextView txt = v.findViewById(R.id.txt_no);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                txt.setVisibility(View.GONE);
                showDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissDialog();
                        txt.setVisibility(View.VISIBLE);
                    }
                },500);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    ProgressDialog dialog;

    private void showDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.show();
    }

    private void dismissDialog() {
        if (dialog != null){
            dialog.cancel();
        }
    }
}