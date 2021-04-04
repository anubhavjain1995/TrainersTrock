package com.trainersstocks.CustomersApp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.ArrayMap;
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
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.SellStockListModel;
import com.trainersstocks.CustomersApp.Models.UserModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.activities.MainLoginUserActivity;
import com.trainersstocks.CustomersApp.adapters.BuyStockTradingAdapter;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockListFragment extends DialogFragment {


    BuyStockTradingAdapter adapter;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.bar)
    ProgressBar bar;

    String buy_sell = "Buy";

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
        if (getArguments() != null) {
            buy_sell = getArguments().getString("buy_sell");
            model = getArguments().getParcelable("model");
        }
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
        if (buy_sell.equalsIgnoreCase("Sell")) {
            getSellList();
        } else {
            getBuyList();
        }
        rv_list.setAdapter(adapter);
        return view;
    }

    private void deleteBuyStock(int i) {
        bar.setVisibility(View.VISIBLE);
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", list.get(i).getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService().stock_buy_delete(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                bar.setVisibility(View.GONE);
                Log.e("TAG", ">> getBuyList  onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getError()) {
                    list.remove(i);
                    adapter.notifyDataSetChanged();
                    Toasty.info(activity, "Deleted Successfully", Toasty.LENGTH_LONG).show();
                } else {
                    Toasty.error(activity, "Failed to delete", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toasty.error(activity, "Failed to delete", Toasty.LENGTH_LONG).show();
                Log.e("TAG", ">> getBuyList  onFailure  " + t.getMessage());

            }
        });

    }

    private void deleteSellStock(int i) {
        bar.setVisibility(View.VISIBLE);
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", list.get(i).getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService().stock_sell_delete(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                bar.setVisibility(View.GONE);
                Log.e("TAG", ">> getBuyList  onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getError()) {
                    list.remove(i);
                    adapter.notifyDataSetChanged();
                    Toasty.info(activity, "Deleted Successfully", Toasty.LENGTH_LONG).show();
                } else {
                    Toasty.error(activity, "Failed to delete", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toasty.error(activity, "Failed to delete", Toasty.LENGTH_LONG).show();
                Log.e("TAG", ">> getBuyList  onFailure  " + t.getMessage());

            }
        });


    }

    private void getBuyList() {
        Log.e("TAG", ">> buy_sell  " + buy_sell);
        bar.setVisibility(View.VISIBLE);
        Call<BuyStockListModel> call = RetrofitService.RetrofitService().stock_buy_lists(model.getUserId());
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

    private void getSellList() {
        Log.e("TAG", ">> buy_sell  " + buy_sell);
        Log.e("TAG", ">> model.getUserId()  " + model.getUserId());
        bar.setVisibility(View.VISIBLE);
        Call<SellStockListModel> call = RetrofitService.RetrofitService().stock_sell_lists(model.getUserId());
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

    @OnClick(R.id.add)
    public void OnAdd() {
/*
        AddUpdateStockFragment addUpdateStockFragment = new AddUpdateStockFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("model", model);
        bundle.putString("buy_sell", buy_sell);
        addUpdateStockFragment.setArguments(bundle);
        addUpdateStockFragment.show(getParentFragmentManager(), "");
*/
    }

    @OnClick(R.id.back)
    public void OnBack() {
        dismiss();
    }


}
