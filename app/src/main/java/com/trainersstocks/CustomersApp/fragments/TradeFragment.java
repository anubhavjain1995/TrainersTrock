package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static androidx.fragment.app.DialogFragment.STYLE_NO_FRAME;

public class TradeFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.ll_one)
    LinearLayoutCompat ll_one;

    @BindView(R.id.ll_two)
    LinearLayoutCompat ll_two;

    @BindView(R.id.layoutsearch)
    TextInputLayout layoutsearch;

    @BindView(R.id.et_search)
    TextInputEditText et_search;

    @BindView(R.id.iv_1)
    AppCompatImageView iv_1;

    @BindView(R.id.iv_2)
    AppCompatImageView iv_2;

    @BindView(R.id.iv_3)
    AppCompatImageView iv_3;

    FragmentManager manager;

    public TradeFragment(FragmentManager manager) {
        this.manager = manager;
    }

    public static TradeFragment newInstance(String param1, String param2, FragmentManager manager) {
        TradeFragment fragment = new TradeFragment(manager);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }


    @OnClick(R.id.submit)
    public void submit() {
        if (et_search.getText().toString().isEmpty()) {
            layoutsearch.setError("Please type something");
        } else {
            ll_two.setVisibility(View.VISIBLE);
            ll_one.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.close)
    public void close() {
        ll_two.setVisibility(View.GONE);
        ll_one.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_1)
    public void iv_1() {
        TradeSearchFragment fragment = new TradeSearchFragment();
        fragment.show(manager, "");
        dismiss();

    }

    @OnClick(R.id.iv_2)
    public void iv_2() {
        TradeSearchFragment fragment = new TradeSearchFragment();
        fragment.show(manager, "");
        dismiss();
    }

    @OnClick(R.id.iv_3)
    public void iv_3() {
        TradeSearchFragment fragment = new TradeSearchFragment();
        fragment.show(manager, "");
        dismiss();
    }


}