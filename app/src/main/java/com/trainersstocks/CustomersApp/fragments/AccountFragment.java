package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.BankDetails;
import com.trainersstocks.CustomersApp.Models.BankModel;
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends MyAbstractFragment implements AdapterView.OnItemSelectedListener {

    String[] bank_name = { "State Bank Of India","Kotak Mahindra"};
    String[] bank_ac = { "32323232232","2323232322"};
   @BindView(R.id.spinner_bank)
    Spinner spinner_bank;
    @BindView(R.id.txt_bank_no)
    TextView txt_bank_no;
    @BindView(R.id.ifsc)
    TextView ifsc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        initlistners();
        if (PreferenceManger.getUserDetailS() != null) {
            getUserBankDeatils();
        }
        return view;
    }

    @Override
    public void initViews(View view) {
        setBank();
    }

    private void setBank() {
        spinner_bank.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                bank_name);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner_bank.setAdapter(ad);
    }

    private void setBankAc(String txt) {

      /*  spinner_bank_ac.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                bank_ac);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner_bank_ac.setAdapter(ad);*/
    }

    @Override
    public void initlistners() {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // setBankAc();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    
    @OnClick(R.id.back)
    public void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void getUserBankDeatils() {
        baseActivity.getDialog().show();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<BankDetails> call = RetrofitService.RetrofitService1().getBankDetails(body);
        call.enqueue(new Callback<BankDetails>() {
            @Override
            public void onResponse(Call<BankDetails> call, Response<BankDetails> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));
                baseActivity.getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                       BankModel model =  response.body().getModel();
                       txt_bank_no.setText(model.getAccount_no());
                        ifsc.setText(model.getAccount_no());
                } else {
                    Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BankDetails> call, Throwable t) {
                Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
                baseActivity.getDialog().dismiss();
            }
        });
    }
    
}