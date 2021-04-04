package com.trainersstocks.CustomersApp.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.MyLog;
import com.hsalf.smileyrating.SmileyRating;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateUsFragment extends Fragment {

    private static final String TAG = RateUsFragment.class.getSimpleName();
    @BindView(R.id.smile_rating)
    SmileyRating smileyRating;



    @BindView(R.id.bar)
    ProgressBar bar;
    private int rateUs = 3;

    public static RateUsFragment newInstance() {
        return new RateUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rateus_fragment, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {

        smileyRating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type) {
                MyLog.LogE(TAG, "the user gave  rating " + type);
                switch (type) {
                    case TERRIBLE:
                        rateUs = 1;
                        break;
                    case BAD:
                        rateUs = 2;

                        break;
                    case OKAY:
                        rateUs = 3;

                        break;
                    case GOOD:
                        rateAtPlayStore();
                        rateUs = 4;

                        break;
                    case GREAT:
                        rateAtPlayStore();
                        rateUs = 5;

                        break;
                    case NONE:
                        break;
                }
            }
        });
    }


    private void rateAtPlayStore() {
        Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
        }

    }



}
