package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.BuyStockModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.MyLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellStockTradingAdapter extends RecyclerView.Adapter<SellStockTradingAdapter.STHolder> {

    Context context;
    ArrayList<BuyStockModel> list;
    RvListUtils rvListUtils;
    String buy_sell;

    public SellStockTradingAdapter(Context context, ArrayList<BuyStockModel> list, RvListUtils rvListUtils, String buy_sell) {
        this.context = context;
        this.list = list;
        this.rvListUtils = rvListUtils;
        this.buy_sell = buy_sell;
    }

    @NonNull
    @Override
    public STHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sell_adapter_stocktrading, parent, false);
        return new STHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull STHolder holder, int position) {

        BuyStockModel model = list.get(position);
        MyLog.LogE("TAG", ">>>  " + new Gson().toJson(model));
        holder.tv_buyprice.setText(model.getBuyPrice());
        holder.tv_buyquantity.setText(model.getBuyQuantity());
        holder.tv_sellprice.setText(model.getSellPrice());
        holder.tv_sellquantity.setText(model.getSellQuantity());
        if (buy_sell.equalsIgnoreCase("Buy")) {
            holder.iv_stock.setImageResource(R.drawable.ic_uplinechart);
        } else {
            holder.iv_stock.setImageResource(R.drawable.ic_downlinechart);
        }
        holder.tv_stockname.setText(model.getStockName());
        holder.tv_date.setText(model.getDate());
        holder.iv_menu.setOnClickListener(v -> {
            showPopupMenu(v, position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class STHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_stock)
        AppCompatImageView iv_stock;

        @BindView(R.id.iv_menu)
        AppCompatImageView iv_menu;

        @BindView(R.id.tv_stockname)
        AppCompatTextView tv_stockname;

        @BindView(R.id.tv_buyprice)
        AppCompatTextView tv_buyprice;

        @BindView(R.id.tv_buyquantity)
        AppCompatTextView tv_buyquantity;

        @BindView(R.id.tv_sellprice)
        AppCompatTextView tv_sellprice;

        @BindView(R.id.tv_sellquantity)
        AppCompatTextView tv_sellquantity;

        @BindView(R.id.tv_date)
        AppCompatTextView tv_date;

        public STHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void showPopupMenu(View view, int i) {

        PopupMenu popup = new PopupMenu(context, view);

        popup.getMenuInflater().inflate(R.menu.stocksmenu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {
                case R.id.action_edit:
                    rvListUtils.Edit(i);
                    break;
                case R.id.action_delete:
                    rvListUtils.Delete(i);
                    break;

            }
            return false;
        });
        popup.show();
    }

    public interface RvListUtils {
        void Edit(int i);

        void Delete(int i);

        void View(int i);
    }
}
