package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.R;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext;

    public SliderPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slider_raw, collection, false);
        AppCompatImageView iv = layout.findViewById(R.id.iv);
        switch (position) {
            case 0:
                Glide.with(mContext).load(R.drawable.slider1).into(iv);
                break;
            case 1:
                Glide.with(mContext).load(R.drawable.slider2).into(iv);

                break;
            case 2:
                Glide.with(mContext).load(R.drawable.slider3).into(iv);

                break;
            case 3:
                Glide.with(mContext).load(R.drawable.slider4).into(iv);

                break;
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



}