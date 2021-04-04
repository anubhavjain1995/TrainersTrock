package com.trainersstocks.CustomersApp.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.trainersstocks.CustomersApp.Models.UserDetailsMModel;
import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawalDialog extends Dialog {

    @BindView(R.id.layoutrefrencenumber)
    TextInputLayout layoutrefrencenumber;

    @BindView(R.id.et_refrencenumber)
    TextInputEditText et_refrencenumber;

    @BindView(R.id.layoutmpin)
    TextInputLayout layoutmpin;

    @BindView(R.id.et_mpin)
    TextInputEditText et_mpin;

    @BindView(R.id.layoutmobilenumber)
    TextInputLayout layoutmobilenumber;

    @BindView(R.id.et_mobilenumber)
    TextInputEditText et_mobilenumber;

    @BindView(R.id.layoutaccountnumber)
    TextInputLayout layoutaccountnumber;

    @BindView(R.id.et_accountnumber)
    TextInputEditText et_accountnumber;

    @BindView(R.id.layoutamount)
    TextInputLayout layoutamount;

    @BindView(R.id.et_amount)
    TextInputEditText et_amount;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.tv_exit)
    TextView tv_exit;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    OnDismiss dismiss;
    UserDetailsMModel userDetailsMModel;

    public WithdrawalDialog(@NonNull Context context, UserDetailsMModel userDetailsMModel, OnDismiss dismiss) {
        super(context);
        this.dismiss = dismiss;
        this.userDetailsMModel = userDetailsMModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.withdraw_dialog);
        ButterKnife.bind(this);
        et_accountnumber.setText(userDetailsMModel.getAccountNo());
        et_mobilenumber.setText(userDetailsMModel.getPhone());

    }


    @OnClick(R.id.tv_exit)
    public void Exit() {
        if (validate(et_refrencenumber.getText().toString(), "refernce number", layoutrefrencenumber) &&
                validate(et_mpin.getText().toString(), "MPIN number", layoutmpin) &&
                validate(et_mobilenumber.getText().toString(), "mobile number", layoutmobilenumber) &&
                validate(et_accountnumber.getText().toString(), "account number", layoutaccountnumber) &&
                validate(et_amount.getText().toString(), "amount", layoutamount)) {
            tv_msg.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                dismiss.OnDismiss();
                dismiss();
            }, 1500);
        }
    }

    @OnClick(R.id.tv_cancel)
    public void Cancel() {
        dismiss();
    }

    public interface OnDismiss {
        void OnDismiss();
    }

    private boolean validate(String s, String err, TextInputLayout layout) {
        if (s.isEmpty()) {
            layout.setError("Please fill " + err);
            return false;
        } else {
            return true;
        }
    }

}
