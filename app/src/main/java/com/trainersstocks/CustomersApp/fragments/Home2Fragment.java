package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.adapters.SliderPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home2Fragment extends MyAbstractFragment {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.iv_reliability)
    AppCompatImageView iv_reliability;

    @BindView(R.id.iv_grade)
    AppCompatImageView iv_grade;

    @BindView(R.id.iv_KindNess)
    AppCompatImageView iv_KindNess;

    @BindView(R.id.iv_Working_Suport)
    AppCompatImageView iv_Working_Suport;

    @BindView(R.id.iv_availability)
    AppCompatImageView iv_availability;

    @BindView(R.id.iv_Research)
    AppCompatImageView iv_Research;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home2Fragment() {
        // Required empty public constructor
    }


    SliderPagerAdapter adapter;

    public static Home2Fragment newInstance(String param1, String param2) {
        Home2Fragment fragment = new Home2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        initlistners();
        return view;
    }

    @Override
    public void initViews(View view) {
        adapter = new SliderPagerAdapter(getContext());
        viewpager.setAdapter(adapter);

    }

    @Override
    public void initlistners() {
        Glide.with(baseActivity).load(R.drawable.trust).into(iv_reliability);
        Glide.with(baseActivity).load(R.drawable.grades).into(iv_grade);
        Glide.with(baseActivity).load(R.drawable.friendship).into(iv_KindNess);
        Glide.with(baseActivity).load(R.drawable.hands).into(iv_Working_Suport);
        Glide.with(baseActivity).load(R.drawable.call).into(iv_availability);
        Glide.with(baseActivity).load(R.drawable.search).into(iv_Research);


    }

}