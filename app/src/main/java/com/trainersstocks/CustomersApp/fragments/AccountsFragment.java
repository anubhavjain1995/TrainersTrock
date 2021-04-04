package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountsFragment extends Fragment {

    @BindView(R.id.iv_noamc)
    AppCompatImageView iv_noamc;

    @BindView(R.id.iv_highexposer)
    AppCompatImageView iv_highexposer;

    @BindView(R.id.iv_tradewithant)
    AppCompatImageView iv_tradewithant;

    @BindView(R.id.iv_bracketorder)
    AppCompatImageView iv_bracketorder;

    @BindView(R.id.iv_elearingkit)
    AppCompatImageView iv_elearingkit;

    @BindView(R.id.iv_antbridge)
    AppCompatImageView iv_antbridge;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        ButterKnife.bind(this, root);
        loadimge();
        return root;
    }

    private void loadimge() {
        Glide.with(getContext()).load(R.drawable.amc).into(iv_noamc);
        Glide.with(getContext()).load(R.drawable.exposer).into(iv_highexposer);
        Glide.with(getContext()).load(R.drawable.ic_people_trading).into(iv_tradewithant);
        Glide.with(getContext()).load(R.drawable.bracket).into(iv_bracketorder);
        Glide.with(getContext()).load(R.drawable.elearning).into(iv_elearingkit);
        Glide.with(getContext()).load(R.drawable.ant).into(iv_antbridge);
    }


}