package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.adapters.SliderPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    SliderPagerAdapter adapter;


    @BindView(R.id.swip)
    SwipeRefreshLayout swip;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.iv_quality)
    AppCompatImageView iv_quality;

    @BindView(R.id.iv_best)
    AppCompatImageView iv_best;

    @BindView(R.id.iv_costomize)
    AppCompatImageView iv_costomize;

    @BindView(R.id.iv_dailyresearch)
    AppCompatImageView iv_dailyresearch;

    @BindView(R.id.iv_chat)
    AppCompatImageView iv_chat;

    @BindView(R.id.iv_fidelity)
    AppCompatImageView iv_fidelity;

    @BindView(R.id.viewpager)
    ViewPager viewpager;


    @BindView(R.id.bar)
    ProgressBar bar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        adapter = new SliderPagerAdapter(getContext());
        viewpager.setAdapter(adapter);
        autoChange();
        loadImage();
        return root;
    }

    private void autoChange() {
        int position = viewpager.getCurrentItem();
        if (position == 3) {
            position = 0;
        } else {
            ++position;
        }
        int finalPosition = position;
        new Handler().postDelayed(() -> {
            viewpager.setCurrentItem(finalPosition);
            autoChange();
        }, 2000);
    }

    private void loadImage() {
        Glide.with(getContext()).load(R.drawable.quality).into(iv_quality);
        Glide.with(getContext()).load(R.drawable.best).into(iv_best);
        Glide.with(getContext()).load(R.drawable.customize).into(iv_costomize);
        Glide.with(getContext()).load(R.drawable.report).into(iv_dailyresearch);
        Glide.with(getContext()).load(R.drawable.chat).into(iv_chat);
        Glide.with(getContext()).load(R.drawable.fidelity).into(iv_fidelity);
    }


}