package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;

public class AboutswithoutloginFragment extends Fragment {


    @BindView(R.id.iv_icon)
    AppCompatImageView iv_icon;

    @BindView(R.id.iv_icon2)
    AppCompatImageView iv_icon2;

    public static AboutswithoutloginFragment newInstance(int index) {
        AboutswithoutloginFragment fragment = new AboutswithoutloginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aboutuswithoutlogin, container, false);
        ButterKnife.bind(this, root);
        Glide.with(getContext()).load(R.drawable.ourteam).into(iv_icon);
        Glide.with(getContext()).load(R.drawable.ourteam).into(iv_icon2);
        return root;
    }


}