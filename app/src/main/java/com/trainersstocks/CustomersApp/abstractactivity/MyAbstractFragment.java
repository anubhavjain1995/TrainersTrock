package com.trainersstocks.CustomersApp.abstractactivity;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;


public abstract class MyAbstractFragment extends Fragment {

    public abstract void initViews(View view);

    public abstract void initlistners();

    public BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            baseActivity = (BaseActivity) context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
