package com.trainersstocks.CustomersApp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.trainersstocks.CustomersApp.Models.LimitsModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.adapters.LimitsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailsFragment extends DialogFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.txt_type)
    AppCompatTextView txt_type;
    String type;
    DetailsFragment(String type){
        this.type=type;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }
    private void initViews() {
        if (type != null) {
            txt_type.setText(type);
        }else {
            txt_type.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        showDialog();
        setData();
    }

    private void setData() {

        ArrayList<LimitsModel> list = new ArrayList<>();
        LimitsModel model1 = new LimitsModel("Deposit","0.14","0.00",true,false,getSubListDeposit());
        list.add(model1);
        LimitsModel model2 = new LimitsModel("Funds Transferred Today","0.00","0.00",false,false,null);
        list.add(model2);
        LimitsModel model3 = new LimitsModel("Funds withdrawal/Allocation","0.00","0.00",false,false,null);
        list.add(model3);
        LimitsModel model4 = new LimitsModel("Collateral","0.00","0.00",true,false,getCollateral());
        list.add(model4);
        LimitsModel model5 = new LimitsModel("Credit For Sale","0.00","0.00",true,false,getCreditSale());
        list.add(model5);
        LimitsModel model6 = new LimitsModel("Option CFS","0.00","0.00",true,false,getOptionCFS());
        list.add(model6);
        LimitsModel model7 = new LimitsModel("Limit Utilization","0.00","0.00",true,false,getLimitUtilization(""));
        list.add(model7);
        LimitsModel model8 = new LimitsModel("Booked Profit/Loss","0.00","0.00",true,false,getLimitUtilization(""));
        list.add(model8);
        LimitsModel model9 = new LimitsModel("MTM Profit/Loss","0.00","",true,false,getLimitUtilization("m"));
        list.add(model9);
        LimitsModel model10 = new LimitsModel("Net Available Funds","0.14","0.00",false,false,null);
        list.add(model10);
        LimitsModel model11 = new LimitsModel("For Trading","0.14","0.00",false,false,null);
        list.add(model11);
        LimitsModel model12 = new LimitsModel("Funds withdrawal/Allocation","0.14","0.00",false,true,null);
        list.add(model12);
        LimitsAdapter adapter = new LimitsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        dismissDialog();
    }

    private ArrayList<LimitsModel> getCollateral() {
        ArrayList<LimitsModel> subList2= new ArrayList<>();
        LimitsModel submodel1 = new LimitsModel("DP Collateral","0.00","0.00",false,false,null);
        subList2.add(submodel1);
        LimitsModel submodel2 = new LimitsModel("Pool Collateral","0.00","0.00",false,false,null);
        subList2.add(submodel2);
        LimitsModel submodel3 = new LimitsModel("SAR Collateral","0.00","0.00",false,false,null);
        subList2.add(submodel3);
        LimitsModel submodel4 = new LimitsModel("DP Pledged Collateral","0.00","0.00",false,false,null);
        subList2.add(submodel4);
        LimitsModel submodel5 = new LimitsModel("Pool Pledged Collateral","0.00","0.00",false,false,null);
        subList2.add(submodel5);
        LimitsModel submodel6 = new LimitsModel("Maximum Collateral Limit","0.00","0.00",false,false,null);
        subList2.add(submodel6);
        return subList2;
    }

    private ArrayList<LimitsModel> getCreditSale() {
        ArrayList<LimitsModel> subList2= new ArrayList<>();
        LimitsModel submodel1 = new LimitsModel("DP CFS","0.00","0.00",false,false,null);
        subList2.add(submodel1);
        LimitsModel submodel2 = new LimitsModel("Maximum CFS Limit","0.00","0.00",false,false,null);
        subList2.add(submodel2);
        LimitsModel submodel3 = new LimitsModel("SAR CFS","0.00","0.00",false,false,null);
        subList2.add(submodel3);
        LimitsModel submodel4 = new LimitsModel("Pool CFS","0.00","0.00",false,false,null);
        subList2.add(submodel4);

        return subList2;
    }

    private ArrayList<LimitsModel> getOptionCFS() {
        ArrayList<LimitsModel> subList2= new ArrayList<>();
        LimitsModel submodel1 = new LimitsModel("Option CFS","0.00","0.00",false,false,null);
        subList2.add(submodel1);
        LimitsModel submodel2 = new LimitsModel("Maximum Option CFS","0.00","0.00",false,false,null);
        subList2.add(submodel2);
        LimitsModel submodel3 = new LimitsModel("Total Trading Power Limit","0.00","0.00",false,false,null);
        subList2.add(submodel3);
        LimitsModel submodel4 = new LimitsModel("Multiplier","0.00","0.00",false,false,null);
        subList2.add(submodel4);

        return subList2;
    }

    private ArrayList<LimitsModel> getLimitUtilization(String m) {
        ArrayList<LimitsModel> subList2= new ArrayList<>();
        LimitsModel submodel1 = new LimitsModel("Commodity Position","0.00","0.00",false,false,null);
        subList2.add(submodel1);
        LimitsModel submodel2 = new LimitsModel("Currency Position","0.00","0.00",false,false,null);
        subList2.add(submodel2);
        LimitsModel submodel3 = new LimitsModel("Derivative Position","0.00","0.00",false,false,null);
        subList2.add(submodel3);
        LimitsModel submodel4 = new LimitsModel("Equity Position","0.00","0.00",false,false,null);
        subList2.add(submodel4);
        if (m.equals("m")){
            LimitsModel submodel5 = new LimitsModel("Total Utilization","0.00","0.00",false,false,null);
            subList2.add(submodel5);
        }

        return subList2;
    }

    private ArrayList<LimitsModel> getSubListDeposit() {
        //SubList Deposit
        ArrayList<LimitsModel> subList1= new ArrayList<>();
        LimitsModel submodel1 = new LimitsModel("Cash Deposit","0.14","0.00",false,false,null);
        subList1.add(submodel1);
        LimitsModel submodel2 = new LimitsModel("Adhoc Deposit","0.00","0.00",false,false,null);
        subList1.add(submodel2);
        LimitsModel submodel3 = new LimitsModel("Overdraft Limit","0.00","0.00",false,false,null);
        subList1.add(submodel3);
        LimitsModel submodel4 = new LimitsModel("Notional Deposit","0.00","0.00",false,false,null);
        subList1.add(submodel4);
        LimitsModel submodel5 = new LimitsModel("Misc. Deposit","0.00","0.00",false,false,null);
        subList1.add(submodel5);
        LimitsModel submodel6 = new LimitsModel("Manual Collateral","0.00","0.00",false,false,null);
        subList1.add(submodel6);
        return subList1;
    }

    ProgressDialog dialog;

    private void showDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.show();
    }

    private void dismissDialog() {
        if (dialog != null){
            dialog.cancel();
        }
    }
    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }
}