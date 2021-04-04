package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeSearchFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.ll_two)
    LinearLayoutCompat ll_two;

    @BindView(R.id.ll_one)
    LinearLayoutCompat ll_one;

    @BindView(R.id.layoutquantity)
    TextInputLayout layoutquantity;

    @BindView(R.id.et_quantity)
    TextInputEditText et_quantity;

    @BindView(R.id.layoutprice)
    TextInputLayout layoutprice;

    @BindView(R.id.et_price)
    TextInputEditText et_price;

    @BindView(R.id.layoutdisclosequantity)
    TextInputLayout layoutdisclosequantity;

    @BindView(R.id.et_disclosequantity)
    TextInputEditText et_disclosequantity;


    private String mParam1;
    private String mParam2;

    @BindView(R.id.toggle)
    RadioGroup toggle;

    @BindView(R.id.nse)
    RadioButton rbtn_nse;

    @BindView(R.id.bse)
    RadioButton rbtn_bse;

    public TradeSearchFragment() {
    }

    public static TradeSearchFragment newInstance(String param1, String param2) {
        TradeSearchFragment fragment = new TradeSearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_trade, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toggle.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.nse:


                    break;

                case R.id.bse:

                    break;


            }
        });
        rbtn_nse.setChecked(true);
    }

    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }

    @OnClick(R.id.submit)
    public void submit() {
        if (validate(et_quantity.getText().toString(), "Quantity", layoutquantity) &&
                validate(et_price.getText().toString(), "Price", layoutprice) &&
                validate(et_disclosequantity.getText().toString(), "disclose quantity", layoutdisclosequantity)
        ){
            ll_two.setVisibility(View.GONE);
            ll_one.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.close)
    public void close() {
        dismiss();
    }

    private boolean validate(String s, String err, TextInputLayout layout) {
        if (s.isEmpty()) {
            layout.setError("Please fill " + err);
            return false;
        } else {
            return true;
        }
    }


}