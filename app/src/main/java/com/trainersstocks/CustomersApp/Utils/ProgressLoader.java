package com.trainersstocks.CustomersApp.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressLoader {
    public static ProgressDialog dialog;
    Context mContext;

    public ProgressLoader(Context mContext) {
        this.mContext = mContext;
    }

    public void showDialog() {
        dialog = new android.app.ProgressDialog(mContext);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null){
            dialog.cancel();
        }
    }
}
