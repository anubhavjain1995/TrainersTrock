package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.trainersstocks.CustomersApp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutCompanyAdapter extends RecyclerView.Adapter<AboutCompanyAdapter.CAHolder> {

    Context context;
    ArrayList<String> list;

    public AboutCompanyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add("Penny stocks are ideal for the average trader");
        list.add("I am grateful for every single dollar that I make, including the losses");
        list.add("Small gains add up over time");
        list.add("Treat every trade like a business");
        list.add("SI am never 100 percent certain about any stock");
        list.add("I will never try to catch the exact bottom or the exact top of any stock move");
        list.add("Small gains add up over time");
        list.add("Cut losses quickly");
        list.add("Small gains add up over time");
        list.add("Buy breakouts");
        list.add("Short breakdowns");
        list.add("Sometimes I like to dip-buy when support holds");
        list.add("I only look at the biggest percent gainers");
        list.add("Keep it simple stupid");
        list.add("Don’t risk a big disaster when you can cut losses");
        list.add("You do not have to trade every day");
        list.add("View this as creating a lifetime of wealth");
        list.add("Never feel uncomfortable in any trade or investment");
        list.add("Always live to trade another day");
        list.add("Use a trading journal");
    }

    @NonNull
    @Override
    public CAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_aboutcompany, parent, false);
        return new CAHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CAHolder holder, int position) {
        int p = position + 1;
        holder.tv_number.setText(p+".");
        holder.tv_text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CAHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_number)
        AppCompatTextView tv_number;

        @BindView(R.id.tv_text)
        AppCompatTextView tv_text;

        public CAHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
