package com.trainersstocks.CustomersApp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordMpinActivity extends AppCompatActivity {
    @BindView(R.id.txt_toolbar)
    AppCompatTextView txt_toolbar;
    @BindView(R.id.ll_change_mpin)
    LinearLayoutCompat ll_change_mpin;
    @BindView(R.id.ll_change_password)
    LinearLayoutCompat ll_change_password;
    String from;
    @BindView(R.id.edt_confirm_mpin)
    TextInputEditText edt_confirm_mpin;
    @BindView(R.id.edt_confirm_password)
    TextInputEditText edt_confirm_password;
    @BindView(R.id.edt_old_password)
    TextInputEditText edt_old_password;
    @BindView(R.id.edt_new_password)
    TextInputEditText edt_new_password;
    @BindView(R.id.edt_new_mpin)
    TextInputEditText edt_new_mpin;
    @BindView(R.id.txt_new_p)
    TextInputLayout txt_new_p;
    @BindView(R.id.txt_old_p)
    TextInputLayout txt_old_p;
    @BindView(R.id.txt_confirm_p)
    TextInputLayout txt_confirm_p;
    @BindView(R.id.txt_new_mpin)
    TextInputLayout txt_new_mpin;
    @BindView(R.id.txt_confirm_mpin)
    TextInputLayout txt_confirm_mpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_mpin);
        ButterKnife.bind(this);
        txt_toolbar = findViewById(R.id.txt_toolbar);
        ll_change_password = findViewById(R.id.ll_change_password);
        ll_change_mpin = findViewById(R.id.ll_change_mpin);
        if (getIntent() != null){
            from = getIntent().getStringExtra("from");
        }
        if (from != null){
            if (from.equalsIgnoreCase("c_p")){
                txt_toolbar.setText("Change Password");
                ll_change_mpin.setVisibility(View.GONE);
                ll_change_password.setVisibility(View.VISIBLE);
            }else{
                txt_toolbar.setText("Change MPIN");
                ll_change_mpin.setVisibility(View.VISIBLE);
                ll_change_password.setVisibility(View.GONE);
            }
        }

        findViewById(R.id.cv_update_mpin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = edt_new_mpin.getText().toString().trim();
                String new_p = edt_confirm_mpin.getText().toString().trim();
                if (old != null && old.length() == 0){
                    txt_new_mpin.setError("Field can't be blank");
                }
                if (new_p != null && new_p.length() == 0){
                    txt_confirm_mpin.setError("Field can't be blank");
                }
                if (!new_p.equals(old)){
                    txt_new_p.setError("MPIN mismatch");
                }
                if (new_p.length() < 4){
                    txt_new_mpin.setError("MPIN must be of 4 digit.");
                }
                if (old.length() < 4){
                 txt_new_mpin.setError("MPIN must be of 4 digit.");
                }
                if (old.length() == 4 && new_p.length() == 4){
                    setMpin(old);
                }
            }
        });

        findViewById(R.id.ll_change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = edt_old_password.getText().toString().trim();
                String new_p = edt_new_password.getText().toString().trim();
                String confirm = edt_confirm_password.getText().toString().trim();
                if (old != null && old.length() == 0){
                    txt_old_p.setError("Field can't be blank");
                }
                if (new_p != null && new_p.length() == 0){
                    txt_new_p.setError("Field can't be blank");
                }
                if (confirm != null && confirm.length() == 0){
                    txt_confirm_p.setError("Field can't be blank");
                }
                if (new_p.equals(old)){
                    txt_new_p.setError("Old and new password can't be same");
                }
                if (!new_p.equals(confirm)){
                    txt_confirm_p.setError("Password mismatch");
                }
                if (old.length() > 0 && new_p.length()>0 && confirm.length()>0 && new_p.equals(confirm)){
                    changePassword(old,new_p);
                }


            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setMpin(String old) {
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        jsonParams.put("mpin", old);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<ResponseModel> call = RetrofitService.RetrofitService1().setMPIN(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));

                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(ChangePasswordMpinActivity.this, "Success", Toasty.LENGTH_LONG).show();
                    finish();
                } else {
                    Toasty.error(ChangePasswordMpinActivity.this, "Failed to load data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toasty.error(ChangePasswordMpinActivity.this, "Failed to load data", Toasty.LENGTH_LONG).show();

            }
        });
    }

    private void changePassword(String old, String new_p) {
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        jsonParams.put("current_pwd", old);
        jsonParams.put("password", new_p);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<ResponseModel> call = RetrofitService.RetrofitService1().changePassword(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));

                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(ChangePasswordMpinActivity.this, "Success", Toasty.LENGTH_LONG).show();
                    finish();
                } else {
                    Toasty.error(ChangePasswordMpinActivity.this, "Password mismatch", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toasty.error(ChangePasswordMpinActivity.this, "Failed to load data", Toasty.LENGTH_LONG).show();

            }
        });
    }
}