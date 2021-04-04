package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectStoreAdapter extends RecyclerView.Adapter<SelectStoreAdapter.MyViewHolder> {
    OnSelect onSelect;
    Context context;

    public SelectStoreAdapter(Context context, OnSelect onSelect) {
        this.onSelect = onSelect;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        AppCompatImageView iv_image;

        @BindView(R.id.check)
        AppCompatImageView check;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnSelect {

    }

}
