package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainersstocks.CustomersApp.Utils.OnItemClickLisner;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.activities.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricingAdapter extends RecyclerView.Adapter<PricingAdapter.MviewHolder> {

    Context context;
    OnItemClickLisner lisner;
    ArrayList<PricingModel> list = new ArrayList<PricingModel>();

    public PricingAdapter(Context context, OnItemClickLisner lisner) {
        this.context = context;
        this.lisner = lisner;
        loadList();
    }

    private void loadList() {
        PricingModel model1 = new PricingModel("Basic Stock Cash", "8500", "18000", "28000", "46000");
        PricingModel model2 = new PricingModel("Basic Stock Future", "8500", "18000", "28000", "46000");
        PricingModel model3 = new PricingModel("Basic Stock Option", "8500", "18000", "28000", "46000");
        PricingModel model4 = new PricingModel("HNI Stock Cash", "8500", "18000", "28000", "46000");
        PricingModel model5 = new PricingModel("HNI Stock Future", "8500", "18000", "28000", "46000");
        PricingModel model6 = new PricingModel("HNI Stock Option", "8500", "18000", "28000", "46000");
        PricingModel model7 = new PricingModel("Index Option", "8500", "18000", "28000", "46000");
        PricingModel model8 = new PricingModel("Index Future", "8500", "18000", "28000", "46000");
        PricingModel model9 = new PricingModel("BTST", "8500", "18000", "28000", "46000");
        PricingModel model10 = new PricingModel("Premium Stock Cash", "8500", "18000", "28000", "46000");
        PricingModel model11 = new PricingModel("Premium Stock Future", "8500", "18000", "28000", "46000");
        PricingModel model12 = new PricingModel("Premium Stock Option", "8500", "18000", "28000", "46000");
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);
        list.add(model6);
        list.add(model7);
        list.add(model8);
        list.add(model9);
        list.add(model10);
        list.add(model11);
        list.add(model12);
    }

    @NonNull
    @Override
    public MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pricing_list_item, parent, false);
        return new MviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MviewHolder holder, int position) {

        holder.tv_name.setText(list.get(position).getStock_name());
        holder.tv_plan_amountm.setText(list.get(position).getMonthly_price());
        holder.tv_plan_amountq.setText(list.get(position).getQuarterly_price());
        holder.tv_plan_amounthy.setText(list.get(position).getHalf_yearly_price());
        holder.tv_plan_amounty.setText(list.get(position).getYearly_price());

        holder.tv_paymonthlhy.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        });

        holder.tv_paymonthlq.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

        });

        holder.tv_paymonthlhy.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

        });

        holder.tv_payyearly.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MviewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;

        @BindView(R.id.tv_plan_amountm)
        AppCompatTextView tv_plan_amountm;

        @BindView(R.id.tv_plan_amountq)
        AppCompatTextView tv_plan_amountq;

        @BindView(R.id.tv_plan_amounthy)
        AppCompatTextView tv_plan_amounthy;

        @BindView(R.id.tv_plan_amounty)
        AppCompatTextView tv_plan_amounty;

        @BindView(R.id.tv_paymonthly)
        AppCompatTextView tv_paymonthly;

        @BindView(R.id.tv_paymonthlq)
        AppCompatTextView tv_paymonthlq;

        @BindView(R.id.tv_paymonthlhy)
        AppCompatTextView tv_paymonthlhy;

        @BindView(R.id.tv_payyearly)
        AppCompatTextView tv_payyearly;

        public MviewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
