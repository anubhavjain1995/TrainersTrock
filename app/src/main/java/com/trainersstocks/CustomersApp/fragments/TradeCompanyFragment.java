package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.adapters.AboutCompanyAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeCompanyFragment extends DialogFragment {

    @BindView(R.id.iv)
    AppCompatImageView iv;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    AboutCompanyAdapter aboutCompanyAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutcompany, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getContext()).load(R.drawable.about_company).into(iv);
        aboutCompanyAdapter = new AboutCompanyAdapter(getContext());
        rv_list.setAdapter(aboutCompanyAdapter);

        return view;
    }

    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }


}