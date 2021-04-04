package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.AppHelper;
import com.trainersstocks.CustomersApp.Utils.OnItemClickLisner;
import com.trainersstocks.CustomersApp.adapters.PricingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricingFragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.bar)
    ProgressBar bar;


    PricingAdapter adapter;

    private boolean isLoading = false;
    private int offset = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pricing_fragment, container, false);
        ButterKnife.bind(this, view);
        adapter = new PricingAdapter(getContext(), (position, o) -> {

        });
        rv_list.setAdapter(adapter);
        AppHelper.setupLoadMore(rv_list, i -> {
            if (isLoading) {
                return;
            } else {

                ++offset;
//                getList("" + offset, lastid);
            }
        });
//        getList("0", "");

        return view;
    }


}
