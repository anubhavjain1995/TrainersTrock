package com.trainersstocks.CustomersApp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trainersstocks.CustomersApp.Models.LimitsModel;
import com.trainersstocks.CustomersApp.R;

import java.util.ArrayList;

public class LimitsAdapter extends RecyclerView.Adapter<LimitsAdapter.MViewHolder> {
    Context mContext;
    ArrayList<LimitsModel> list;

    public LimitsAdapter(Context mContext, ArrayList<LimitsModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_three_row_layout,parent,false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        if (list != null && list.size() >0){
            LimitsModel model = list.get(position);
            holder.txt_desc.setText(model.getDesc());
            holder.txt_trading.setText(model.getTrading());
            holder.txt_mf.setText(model.getMf());

            if (model.isShowArrow()){
                holder.img_arrow.setVisibility(View.VISIBLE);
            }else{
                holder.img_arrow.setVisibility(View.INVISIBLE);
            }
            if (model.isBold()){
                holder.txt_desc.setTypeface(holder.txt_desc.getTypeface(), Typeface.BOLD);
                holder.txt_trading.setTypeface(holder.txt_trading.getTypeface(), Typeface.BOLD);
                holder.txt_mf.setTypeface(holder.txt_mf.getTypeface(), Typeface.BOLD);
            }else{
                holder.txt_desc.setTypeface(holder.txt_desc.getTypeface(), Typeface.NORMAL);
                holder.txt_trading.setTypeface(holder.txt_trading.getTypeface(), Typeface.NORMAL);
                holder.txt_mf.setTypeface(holder.txt_mf.getTypeface(), Typeface.NORMAL);
            }
            holder.img_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.isShow) {
                        holder.isShow = false;
                        holder.recyclerView.setVisibility(View.GONE);
                        holder.l_top.setBackgroundResource(R.color.white);
                        holder.img_arrow.setRotation(0);
                    }else {
                        holder.isShow = true;
                        holder.img_arrow.setRotation(180);
                        holder.l_top.setBackgroundResource(R.color.graycolor);
                        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
                        holder.adapter = new LimitsAdapter(mContext,model.getLimitsList());
                        holder.recyclerView.setAdapter(holder.adapter);
                        holder.recyclerView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView txt_desc,txt_trading,txt_mf;
        RecyclerView recyclerView;
        ImageView img_arrow;
        LimitsAdapter adapter;
        boolean isShow=false;
        LinearLayout l_top;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_desc = itemView.findViewById(R.id.txt_description);
            txt_trading = itemView.findViewById(R.id.txt_trading);
            txt_mf = itemView.findViewById(R.id.txt_mf);
            img_arrow = itemView.findViewById(R.id.img_arrow);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            l_top = itemView.findViewById(R.id.l_top);
        }
    }

    /*public ArrayList<LimitsModel> getList(){
        ArrayList<LimitsModel> list = new ArrayList<>();
        LimitsModel model1 = new LimitsModel("Deposit","0.14","0.00",false,false);
        list.add(model1);
        LimitsModel model2 = new LimitsModel("Funds Transferred Today","0.00","0.00",false,false);
        list.add(model2);
        LimitsModel model3 = new LimitsModel("Funds withdrawal/Allocation","0.00","0.00",false,false);
        list.add(model3);
        LimitsModel model4 = new LimitsModel("Collateral","0.00","0.00",true,false);
        list.add(model4);
        LimitsModel model5 = new LimitsModel("Credit For Sale","0.00","0.00",true,false);
        list.add(model5);
        LimitsModel model6 = new LimitsModel("Option CFS","0.00","0.00",true,false);
        list.add(model6);
        LimitsModel model7 = new LimitsModel("Limit Utilization","0.00","0.00",true,false);
        list.add(model7);
        LimitsModel model8 = new LimitsModel("Booked Profit/Loss","0.00","0.00",true,false);
        list.add(model8);
        LimitsModel model9 = new LimitsModel("MTM Profit/Loss","0.00","",true,false);
        list.add(model9);
        LimitsModel model10 = new LimitsModel("Net Available Funds","0.14","0.00",false,false);
        list.add(model10);
        LimitsModel model11 = new LimitsModel("For Trading","0.14","0.00",false,false);
        list.add(model11);

        return list;
    }*/
}
