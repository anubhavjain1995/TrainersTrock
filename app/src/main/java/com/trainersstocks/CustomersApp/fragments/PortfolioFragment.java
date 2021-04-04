package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.CustomerPayDetail;
import com.trainersstocks.CustomersApp.Models.CustomerPayDetailModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortfolioFragment extends MyAbstractFragment {

    @BindView(R.id.toggle)
    RadioGroup toggle;

    @BindView(R.id.product)
    RadioButton rbtn_products;

    @BindView(R.id.post)
    RadioButton rbtn_post;

    @BindView(R.id.tv_customernetloss)
    AppCompatTextView tv_customernetloss;

    @BindView(R.id.tv_customernetprofit)
    AppCompatTextView tv_customernetprofit;

    @BindView(R.id.tv_customerpay)
    AppCompatTextView tv_customerpay;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public PortfolioFragment() {
    }

    public static PortfolioFragment newInstance(String param1, String param2) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        initlistners();
        fragmentList.add(new SellStockListFragment());
        fragmentList.add(new BuyStockListFragment());
        //  pager.setAdapter(adapter);

        return view;
    }

    @Override
    public void initViews(View view) {
        toggle.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.post:
                    getFragmentManager().beginTransaction().replace(R.id.pager, new BuyStockListFragment()).commit();
                    break;

                case R.id.product:
                    getFragmentManager().beginTransaction().replace(R.id.pager, new SellStockListFragment()).commit();
                    break;


            }
        });
        rbtn_post.setChecked(true);
    }

    @Override
    public void initlistners() {
        getCustomerPay();
    }


    private void getCustomerPay() {
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        Call<CustomerPayDetail> call = RetrofitService.RetrofitService().customer_pay_details(body);
        call.enqueue(new Callback<CustomerPayDetail>() {
            @Override
            public void onResponse(Call<CustomerPayDetail> call, Response<CustomerPayDetail> response) {
                Log.e("TAG", ">>onResponse customer_pay_details " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getError()) {
                    setCustomerpay(response.body().getData());
                } else {
                    Toasty.error(getContext(), "Customer pay detail not found", Toasty.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerPayDetail> call, Throwable t) {
                Toasty.error(getContext(), "Customer pay detail not found", Toasty.LENGTH_SHORT).show();
                Log.e("TAG", ">>onFailure  " + t.getMessage());
            }
        });
    }

    private void setCustomerpay(CustomerPayDetailModel data) {
        tv_customerpay.setText("Customer pay\n" + data.getCustomerPay());
        tv_customernetloss.setText("Net Loss\n" + data.getNetLoss());
        tv_customernetprofit.setText("Net Profit\n" + data.getNetProfit());
    }
}