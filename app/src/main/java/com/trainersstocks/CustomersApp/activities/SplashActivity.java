package com.trainersstocks.CustomersApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MyAbstractActivity {

    @BindView(R.id.iv)
    AppCompatImageView iv;

    @BindView(R.id.iv_logo)
    AppCompatImageView iv_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initview();
        listners();

    }

    @Override
    public void initview() {
        Glide.with(this).load(R.drawable.logo).into(iv_logo);
        Glide.with(this).load(R.drawable.splash).into(iv);
    }

    @Override
    public void listners() {
        new Handler().postDelayed(() -> {
            if (PreferenceManger.getUserDetailS() != null) {
                startActivity(new Intent(this, MainLoginUserActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 2000);
    }

}