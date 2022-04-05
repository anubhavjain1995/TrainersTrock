package com.trainersstocks.CustomersApp.fragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.trainersstocks.CustomersApp.Models.LimitsModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.ProgressLoader;
import com.trainersstocks.CustomersApp.abstractactivity.BaseActivity;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.adapters.LimitsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LimitFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    String[] courses = { "All Segments","NBFC"};
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.l_total_funds)
    LinearLayout l_total_funds;

    String type = courses[0];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_limit, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                courses);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.setAdapter(ad);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        // setData();
    }

    private void setData() {

        ArrayList<LimitsModel> list = new ArrayList<>();

        LimitsModel model1 = new LimitsModel("Deposit","0.14","0.00",false,false,null);
        list.add(model1);
        LimitsModel model2 = new LimitsModel("Funds Transferred Today","0.00","0.00",false,false,null);
        list.add(model2);
        LimitsModel model3 = new LimitsModel("Collateral","0.00","0.00",false,false,null);
        list.add(model3);
        LimitsModel model4 = new LimitsModel("Limit Utilization","0.00","0.00",false,false,null);
        list.add(model4);
        LimitsModel model5 = new LimitsModel("Booked Profit/Loss","0.00","0.00",false,false,null);
        list.add(model5);
        LimitsModel model6 = new LimitsModel("MTM Profit/Loss","0.00","",false,false,null);
        list.add(model6);
        LimitsAdapter adapter = new LimitsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.back)
    public void Back() {
        dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        recyclerView.setVisibility(View.GONE);
        l_total_funds.setVisibility(View.GONE);
        new ProgressLoader(getContext()).showDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    type=courses[i];
                    new ProgressLoader(getContext()).dismissDialog();
                    setData();
                    l_total_funds.setVisibility(View.VISIBLE);
                }
            },2000);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @OnClick(R.id.cv_details)
    public void openDetails() {
        DetailsFragment fragment = new DetailsFragment(type);
        fragment.show(getChildFragmentManager(),"");
    }
}