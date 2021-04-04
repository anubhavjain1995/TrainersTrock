package com.trainersstocks.CustomersApp.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.BaseActivity;

public class NotificationActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    @BindView(R.id.toolbar_notification)
    Toolbar toolbar_notification;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);
        ButterKnife.bind(this);
        setToolbar(toolbar_notification);
        showBackButton();


    }



}