package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainersstocks.CustomersApp.R;
import com.google.android.material.tabs.TabLayout;

public class FavouriteFragment extends Fragment {
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.tabs)
    TabLayout tabs;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
