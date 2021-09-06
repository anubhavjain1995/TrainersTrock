package com.trainersstocks.CustomersApp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.cashfree.pg.CFPaymentService;
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.TokenMainModel;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.Models.UserDetailsMModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.INVESTDialog;
import com.trainersstocks.CustomersApp.Utils.MPINDialog;
import com.trainersstocks.CustomersApp.Utils.MyLog;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.Utils.WithdrawalDialog;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;

public class ViewProfileFragment extends MyAbstractFragment implements INVESTDialog.OnDismiss {

    String stage = "TEST";

    private String appID = "43688ed00480b0435fcfd92ca88634";
    private String Secret_Key = "9bde11412522247065d600c31baa5cd17873eb88";


    @BindView(R.id.civ)
    CircleImageView civ;

    @BindView(R.id.tv_firstname)
    AppCompatTextView tv_firstname;

    @BindView(R.id.tv_lastname)
    AppCompatTextView tv_lastname;

    @BindView(R.id.tv_emailname)
    AppCompatTextView tv_email;

    @BindView(R.id.tv_mobile)
    AppCompatTextView tv_mobile;

    @BindView(R.id.tv_dob)
    AppCompatTextView tv_dob;

    @BindView(R.id.tv_account)
    AppCompatTextView tv_account;

    @BindView(R.id.tv_pannu)
    AppCompatTextView tv_pannu;

    @BindView(R.id.tv_aadharnumber)
    AppCompatTextView tv_aadharnumber;

    @BindView(R.id.tv_accountstatus)
    AppCompatTextView tv_accountstatus;

    @BindView(R.id.tv_password)
    AppCompatTextView tv_password;

    CFPaymentService cfPaymentService;
    private String amount = "0";
    private UserDetailsMModel userDetail = null;
    private String order_id = "";

    public ViewProfileFragment() {
        // Required empty public constructor
    }

    public static ViewProfileFragment newInstance(String param1, String param2) {
        ViewProfileFragment fragment = new ViewProfileFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        initlistners();
        Glide.with(getContext()).load(R.drawable.ic_male_avatar).into(civ);
        getUserDeatils();

        return view;
    }

    private void getUserDeatils() {
        baseActivity.getDialog().show();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<UMModel> call = RetrofitService.RetrofitService().getUserdetail(body);
        call.enqueue(new Callback<UMModel>() {
            @Override
            public void onResponse(Call<UMModel> call, Response<UMModel> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));
                baseActivity.getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                    userDetail = response.body().getModel();
                    setData(response.body().getModel());
                } else {
                    Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UMModel> call, Throwable t) {
                Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
                baseActivity.getDialog().dismiss();
            }
        });
    }

    private void setData(UserDetailsMModel model) {
        if (model == null) {
            return;
        }
        MyLog.LogE("TAG", ">>   " + new Gson().toJson(model));
        tv_firstname.setText(model.getFirstName());
        tv_lastname.setText(model.getLastName());
        tv_email.setText(model.getEmail());
        tv_mobile.setText(model.getPhone());
        tv_dob.setText(model.getDob());
        tv_account.setText(model.getAccountNo());
        tv_pannu.setText(model.getPanCard());
        tv_aadharnumber.setText(model.getAadharNo());
        tv_password.setText(model.getViewPassword());
        if (model.getIsActive().equalsIgnoreCase("1")) {
            tv_accountstatus.setText("Active");
        } else {
            tv_accountstatus.setText("Deactive");
        }


    }

    @OnClick(R.id.tv_invest)
    public void Invest() {
        new INVESTDialog(baseActivity, this).show();

    }

    @OnClick(R.id.tv_withdrawal)
    public void Withdrawal() {
        new WithdrawalDialog(baseActivity, userDetail, (String reference, String mpin, String mobile, String account, String amount) -> {
            withdraw(userDetail.getEmail(),reference,mpin,mobile,account,amount);
        }).show();
    }

    @OnClick(R.id.tv_mpin)
    public void mpin() {

        new MPINDialog(baseActivity, null).show();

    }

    @OnClick(R.id.edit_user_profile)
    public void Edit_User_Profile() {


    }


    @Override
    public void initViews(View view) {
        cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);

    }

    @Override
    public void initlistners() {

    }

    @Override
    public void OnDismiss(String amount) {
        if (userDetail == null) {
            return;
        }
        this.amount = amount;
        MyLog.LogE("AMOUNT", ">>> >>>   " + amount);
        Calendar calendar = Calendar.getInstance();
        String order_id = calendar.getTimeInMillis() + userDetail.getUserId();
        this.order_id = order_id;
        Map<String, String> jsonParams = new ArrayMap<>();

        jsonParams.put("orderId", order_id);
        jsonParams.put("orderAmount", amount);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<TokenMainModel> call = RetrofitService.RetrofitService().getToken(body);
        call.enqueue(new Callback<TokenMainModel>() {
            @Override
            public void onResponse(Call<TokenMainModel> call, Response<TokenMainModel> response) {
                Log.e("TAG", ">>onResponse  " + response.code());
                Log.e("TAG", ">>onResponse  " + response.message());
                Log.e("TAG", ">>onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().isError()) {
                    cfPaymentService.doPayment(baseActivity, getInputParams(), response.body().getData().getCftoken(), stage);
                } else {
                    Toasty.error(getContext(), "Try Again!", Toasty.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenMainModel> call, Throwable t) {
                Toasty.error(getContext(), t.getMessage(), Toasty.LENGTH_SHORT).show();
                Log.e("TAG", ">>onFailure  " + t.getMessage());

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLog.LogE("TAG", "API Response : " + requestCode);
        MyLog.LogE("TAG", "API Response : " + resultCode);
        MyLog.LogE("TAG", "API Response : " + new Gson().toJson(data));
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        MyLog.LogE("TAG", key + " : " + bundle.getString(key));
                    }
                }
        }
    }


    private Map<String, String> getInputParams() {

        /*
         * appId will be available to you at CashFree Dashboard. This is a unique
         * identifier for your app. Please replace this appId with your appId.
         * Also, as explained below you will need to change your appId to prod
         * credentials before publishing your app.
         */
        String orderNote = "trainers stocks";
        String customerName = userDetail.getFirstName();
        String customerPhone = userDetail.getPhone();
        String customerEmail = userDetail.getEmail();

        Map<String, String> params = new HashMap<>();
        MyLog.LogE("AMOUNT", ">>> >>>   " + amount);

        params.put(PARAM_APP_ID, appID);
        params.put(PARAM_ORDER_ID, order_id);
        params.put(PARAM_ORDER_AMOUNT, amount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);
        params.put(PARAM_ORDER_CURRENCY, "INR");
        return params;
    }


    private void withdraw(String userDetailEmail, String reference, String mpin, String mobile, String account, String amount) {
        baseActivity.getDialog().show();

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("email", userDetailEmail);
        jsonParams.put("reference_number", reference);
        jsonParams.put("mpin", mobile);
        jsonParams.put("phone", account);
        jsonParams.put("account_no", amount);
        jsonParams.put("amount", mpin);
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService1().withdrawalSubmit(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">> hariom " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                baseActivity.getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(baseActivity, "Request sent successfully", Toast.LENGTH_SHORT, true).show();

                } else {
                    Toasty.error(baseActivity, "Failed to request.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("TAG", ">> Hariom  " + t.getMessage());
                baseActivity.getDialog().dismiss();
                Toasty.error(baseActivity, "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });


    }


}