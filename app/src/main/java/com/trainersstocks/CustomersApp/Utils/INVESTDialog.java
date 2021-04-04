package com.trainersstocks.CustomersApp.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class INVESTDialog extends Dialog {

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.iv_)
    AppCompatImageView iv_;

    @BindView(R.id.iv)
    AppCompatImageView iv;


    OnDismiss dismiss;
    Context context;

    public INVESTDialog(@NonNull Context context, OnDismiss dismiss) {
        super(context);
        this.context = context;
        this.dismiss = dismiss;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.invest_dialog);
        ButterKnife.bind(this);
        Glide.with(context).load(R.drawable.qr).into(iv);
        Glide.with(context).load(R.drawable.paymode).into(iv_);
    }


    @OnClick(R.id.tv_cancel)
    public void Cancel() {
        dismiss();
    }

    public interface OnDismiss {
        void OnDismiss(String amount);

    }

}
