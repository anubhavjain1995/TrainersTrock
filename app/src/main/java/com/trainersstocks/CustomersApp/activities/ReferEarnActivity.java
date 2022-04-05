package com.trainersstocks.CustomersApp.activities;

import static com.trainersstocks.CustomersApp.Utils.ShareUtils.shareWhatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ReferEarnActivity extends AppCompatActivity {

    private String coupon_code="TS123456";

    private String msg = "Trainers Stock is offering Free Account opening and Special Brokerage Rates – Free for Delivery Trades | Flat ₹20 per order for Intraday & FNO.\n" +
            "\n" +
            "And because I referred you, you also get these special deals for your 1st month:\n" +
            "1. Brokerage Cashback upto Rs 500*\n" +
            "2. Free ARQ Prime Premium Advisory\n" +
            "3. Smart Money access worth Rs 2000\n" +
            "\n" +
            "Hurry! Join me on Trainers Stock in next 48 hours to avail.\n" +
            "Please enter Introducer code : "+coupon_code+"\n" +
            "T&C apply.\n" +
            " Add url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public void share() {

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Coupon Mart");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
        emailIntent.setType("text/plain");
        startActivity(Intent.createChooser(emailIntent, "Send to friend"));
    }

    @OnClick(R.id.iv_whatsapp)
    public void Whatsshare() {
        shareWhatsapp(ReferEarnActivity.this, msg, "");
    }


    @OnClick(R.id.invite)
    public void share(View view) {
        share();
    }
    @OnClick(R.id.back)
    public void back(View view) {
        onBackPressed();
    }
}