package com.trainersstocks.CustomersApp.fragments;

import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.trainersstocks.CustomersApp.Utils.ShareUtils.shareFacebook;
import static com.trainersstocks.CustomersApp.Utils.ShareUtils.shareTwitter;
import static com.trainersstocks.CustomersApp.Utils.ShareUtils.shareWhatsapp;

public class ReferandEarnFragment extends Fragment {

    @BindView(R.id.tv_code_copy)
    AppCompatTextView tv_code_copy;

    @BindView(R.id.tv_code)
    AppCompatTextView tv_code;
    private String msg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.referandearn_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    @OnClick(R.id.invite)
    public void share() {
//        shareLink();

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Coupon Mart");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
        emailIntent.setType("text/plain");
        startActivity(Intent.createChooser(emailIntent, "Send to friend"));
    }

    @OnClick(R.id.iv_whatsapp)
    public void Whatsshare() {
        shareWhatsapp(getActivity(), msg, "");
    }


    @OnClick(R.id.iv_facebook)
    public void Facebook() {
        shareFacebook(getActivity(), msg, "");
    }

    @OnClick(R.id.iv_twitter)
    public void Twitter() {
        shareTwitter(getActivity(), msg, "", "", "");
    }


}
