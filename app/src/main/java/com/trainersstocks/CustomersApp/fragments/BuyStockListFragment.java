package com.trainersstocks.CustomersApp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.trainersstocks.CustomersApp.Models.BuyStockListModel;
import com.trainersstocks.CustomersApp.Models.BuyStockModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.activities.MainLoginUserActivity;
import com.trainersstocks.CustomersApp.adapters.BuyStockTradingAdapter;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyStockListFragment extends DialogFragment {


    BuyStockTradingAdapter adapter;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.bar)
    ProgressBar bar;

    String buy_sell = "Buy";

    MainLoginUserActivity activity;
    private ArrayList<BuyStockModel> list = new ArrayList<>();

    public static BuyStockListFragment newInstance(String param1, String param2) {
        BuyStockListFragment fragment = new BuyStockListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainLoginUserActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);
        ButterKnife.bind(this, view);
        adapter = new BuyStockTradingAdapter(getContext(), list, new BuyStockTradingAdapter.RvListUtils() {
            @Override
            public void Edit(int i) {
                /*AddUpdateStockFragment addUpdateStockFragment = new AddUpdateStockFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("stockmodel", list.get(i));
                bundle.putParcelable("model", model);
                bundle.putString("buy_sell", buy_sell);
                addUpdateStockFragment.setArguments(bundle);
                addUpdateStockFragment.show(getParentFragmentManager(), "");*/
            }

            @Override
            public void Delete(int i) {
/*
                DeleteUserDialog deleteUserDialog = new DeleteUserDialog(getContext(), () -> {
                    if (buy_sell.equalsIgnoreCase("Buy")) {
                        deleteBuyStock(i);
                    } else {
                        deleteSellStock(i);
                    }
                });
                deleteUserDialog.show();
*/
            }

            @Override
            public void View(int i) {

            }
        }, buy_sell);
        rv_list.setAdapter(adapter);
        getBuyList();
        return view;
    }


    private void getBuyList() {
        Log.e("TAG", ">> buy_sell  " + buy_sell);
        Log.e("TAG", ">> buy_sell  User ID " + PreferenceManger.getUserDetailS().getUserDetails().getId());
        bar.setVisibility(View.VISIBLE);
        Call<BuyStockListModel> call = RetrofitService.RetrofitService().stock_buy_lists(PreferenceManger.getUserDetailS().getUserDetails().getId());
        call.enqueue(new Callback<BuyStockListModel>() {
            @Override
            public void onResponse(Call<BuyStockListModel> call, Response<BuyStockListModel> response) {
                bar.setVisibility(View.GONE);
                Log.e("TAG", ">> getBuyList  onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getError()) {
                    list.clear();
                    list.addAll(response.body().getList());
                    adapter.notifyDataSetChanged();
                } else {
                    Toasty.error(activity, "Data Not Found", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BuyStockListModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toasty.error(activity, "Data Not Found", Toasty.LENGTH_LONG).show();
                Log.e("TAG", ">> getBuyList  onFailure  " + t.getMessage());

            }
        });
    }


}
