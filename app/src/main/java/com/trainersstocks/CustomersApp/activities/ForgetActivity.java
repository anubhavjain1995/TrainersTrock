package com.trainersstocks.CustomersApp.activities;

import android.os.Bundle;
import android.view.Menu;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.BaseActivity;

import butterknife.ButterKnife;

public class ForgetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        showBackButton();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}
