package com.trainersstocks.CustomersApp.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.trainersstocks.CustomersApp.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogoutDialog extends Dialog {
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.tv_exit)
    TextView tv_exit;

    OnDismiss dismiss;

    public LogoutDialog(@NonNull Context context, OnDismiss dismiss) {
        super(context);
        this.dismiss = dismiss;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.logoutdialog);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_exit)
    public void Exit() {
        dismiss.OnDismiss();
        dismiss();
    }

    @OnClick(R.id.tv_cancel)
    public void Cancel() {
        dismiss();
    }

    public interface OnDismiss {
        void OnDismiss();
    }

}
