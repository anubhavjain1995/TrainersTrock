package com.trainersstocks.CustomersApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.trainersstocks.CustomersApp.Models.LoginModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.AppHelper;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends MyAbstractActivity {

    @BindView(R.id.layoutusername)
    TextInputLayout layoutusername;

    @BindView(R.id.et_username)
    TextInputEditText et_username;

    @BindView(R.id.layoutpassword)
    TextInputLayout layoutpassword;

    @BindView(R.id.et_password)
    TextInputEditText et_password;

    @BindView(R.id.iv)
    AppCompatImageView iv;

    @BindView(R.id.iv_logo)
    AppCompatImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initview();
        listners();

    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {
        Glide.with(this).load(R.drawable.logo).into(iv_logo);
        Glide.with(this).load(R.drawable.splash).into(iv);
    }

    public void Login(View view) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (AppHelper.Validate(username, "please enter username/id.", layoutusername) &&
                AppHelper.Validate(password, "please enter password.", layoutpassword)) {
            logIn(username, password);
        }
    }

    private void logIn(String username, String password) {
        getDialog().show();

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("email", username);
        jsonParams.put("password", password);
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        Call<LoginModel> call = RetrofitService.RetrofitService1().admin_login(body);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT, true).show();
                    PreferenceManger.setUserDetailS(response.body().getData());
                    startActivity(new Intent(LoginActivity.this, MainLoginUserActivity.class));
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    Toasty.error(LoginActivity.this, "Failed to login.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                getDialog().dismiss();
                Toasty.error(LoginActivity.this, "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });


    }

    public void Skip(View view) {
        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}