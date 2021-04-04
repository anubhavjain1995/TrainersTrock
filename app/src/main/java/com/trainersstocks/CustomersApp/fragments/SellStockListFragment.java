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

import com.trainersstocks.CustomersApp.Models.BuyStockModel;
import com.trainersstocks.CustomersApp.Models.SellStockListModel;
import com.trainersstocks.CustomersApp.Models.UserModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.activities.MainLoginUserActivity;
import com.trainersstocks.CustomersApp.adapters.BuyStockTradingAdapter;
import com.trainersstocks.CustomersApp.adapters.SellStockTradingAdapter;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellStockListFragment extends DialogFragment {


    SellStockTradingAdapter adapter;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.bar)
    ProgressBar bar;

    String buy_sell = "Sell";

    public static SellStockListFragment newInstance(String param1, String param2) {
        SellStockListFragment fragment = new SellStockListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    MainLoginUserActivity activity;
    private ArrayList<BuyStockModel> list = new ArrayList<>();
    private UserModel model;

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
        adapter = new SellStockTradingAdapter(getContext(), list, new SellStockTradingAdapter.RvListUtils() {
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
        getSellList();
        return view;
    }


    private void getSellList() {
        bar.setVisibility(View.VISIBLE);
        Call<SellStockListModel> call = RetrofitService.RetrofitService().stock_sell_lists(PreferenceManger.getUserDetailS().getUserDetails().getId());
        call.enqueue(new Callback<SellStockListModel>() {
            @Override
            public void onResponse(Call<SellStockListModel> call, Response<SellStockListModel> response) {
                bar.setVisibility(View.GONE);
                Log.e("TAG", ">> getSellList  onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getError()) {
                    list.clear();
                    list.addAll(response.body().getList());
                    adapter.notifyDataSetChanged();
                } else {
                    Toasty.error(activity, "Data Not Found", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SellStockListModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Log.e("TAG", ">> getSellList  onFailure  " + t.getMessage());
                Toasty.error(activity, "Data Not Found", Toasty.LENGTH_LONG).show();
            }
        });

    }


}
