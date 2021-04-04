package com.trainersstocks.CustomersApp.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceptActivity extends MyAbstractActivity {

    @BindView(R.id.webview)
    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);
        ButterKnife.bind(this);


    }

    public void OnAccept(View view) {


    }

    public void OnDecline(View view) {


    }


    @Override
    public void initview() {


    }

    @Override
    public void listners() {


    }
}