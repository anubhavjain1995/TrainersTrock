package com.trainersstocks.CustomersApp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFundFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_fund, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {

    }

    @OnClick(R.id.btn_add_funds)
    public void addFunds(){
        EnterAmountFragment fragment = new EnterAmountFragment();
        fragment.show(getChildFragmentManager(), "");

    }
}