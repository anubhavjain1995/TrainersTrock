package com.trainersstocks.CustomersApp.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.cashfree.pg.CFPaymentService;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.trainersstocks.CustomersApp.Models.LoginModel;
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
import com.trainersstocks.CustomersApp.activities.LoginActivity;
import com.trainersstocks.CustomersApp.activities.MainLoginUserActivity;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    private  String SELECT_BASE_64_PATH = "";
    String stage = "TEST";

    private String appID = "43688ed00480b0435fcfd92ca88634";
    private String Secret_Key = "9bde11412522247065d600c31baa5cd17873eb88";


    @BindView(R.id.civ)
    CircleImageView civ;

    @BindView(R.id.txt_name)
    AppCompatTextView tv_firstname;

    @BindView(R.id.txt_id)
    AppCompatTextView txt_id;

    @BindView(R.id.tv_emailname)
    AppCompatEditText tv_email;

    @BindView(R.id.tv_mobile)
    AppCompatEditText tv_mobile;

    @BindView(R.id.tv_dob)
    AppCompatEditText tv_dob;

    /*@BindView(R.id.tv_account)
    AppCompatTextView tv_account;*/

    @BindView(R.id.tv_pannu)
    AppCompatEditText tv_pannu;

    @BindView(R.id.tv_aadhar)
    AppCompatEditText tv_aadharnumber;

    @BindView(R.id.rl_edit_btn)
    RelativeLayout rl_edit_btn;

    @BindView(R.id.rl_submit_btn)
    RelativeLayout rl_submit_btn;


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
        txt_id.setText(model.getUserName());
        tv_email.setText(model.getEmail());
        tv_mobile.setText(model.getPhone());
        tv_dob.setText(model.getDob());
       // tv_account.setText(model.getAccountNo());
        tv_pannu.setText(model.getPanCard());
        tv_aadharnumber.setText(model.getAadharNo());
        //tv_password.setText(model.getViewPassword());
        /*if (model.getIsActive().equalsIgnoreCase("1")) {
            tv_accountstatus.setText("Active");
        } else {
            tv_accountstatus.setText("Deactive");
        }*/


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
        /*if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        MyLog.LogE("TAG", key + " : " + bundle.getString(key));
                    }
                }
        }*/
        if (resultCode == Activity.RESULT_OK) {
            String uri = ImagePicker.Companion.getFilePath(data);
            Uri s_uri = Uri.parse(uri);
            civ.setImageURI(s_uri);
            this.SELECT_BASE_64_PATH = getBase64FromFile(uri);

            uploadPic();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("profile_image", "data:image/jpeg;base64," + SELECT_BASE_64_PATH);
            } catch (JSONException e) {
                e.printStackTrace();
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
        jsonParams.put("mpin", mpin);
        jsonParams.put("phone", mobile);
        jsonParams.put("account_no", account);
        jsonParams.put("amount", amount);

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

    @OnClick(R.id.rl_edit_btn)
    public void editProfile(){
        tv_mobile.setEnabled(true);
        tv_aadharnumber.setEnabled(true);
        tv_dob.setEnabled(true);
        tv_pannu.setEnabled(true);
        tv_email.setEnabled(true);
        tv_email.setFocusable(true);
        rl_edit_btn.setVisibility(View.GONE);
        rl_submit_btn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_dob)
    public void openCalender() {
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.setTime(new Date());
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.my_dialog_theme, (view, year1, monthOfYear, dayOfMonth) -> {
            //do something with the date
            String finalDay = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
            String finalMonth = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : String.valueOf((monthOfYear + 1));
            tv_dob.setText(finalDay+"/"+ finalMonth + "/" +year1 );
        }, year, month, day);

        Calendar maxCal = Calendar.getInstance();
        maxCal.add(Calendar.YEAR, -12);
        Calendar minCal = Calendar.getInstance();
        minCal.add(Calendar.YEAR, -99);
        dialog.getDatePicker().setMaxDate(maxCal.getTimeInMillis());
        dialog.getDatePicker().setMinDate(minCal.getTimeInMillis());
        dialog.show();
    }

    @OnClick(R.id.rl_submit_btn)
    public void submitDetails(){
        tv_mobile.setEnabled(false);
        tv_aadharnumber.setEnabled(false);
        tv_dob.setEnabled(false);
        tv_pannu.setEnabled(false);
        tv_email.setEnabled(false);
        rl_edit_btn.setVisibility(View.VISIBLE);
        rl_submit_btn.setVisibility(View.GONE);

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", userDetail.getUserId());
        jsonParams.put("first_name", userDetail.getFirstName());
        jsonParams.put("last_name", userDetail.getLastName());
        jsonParams.put("email", tv_email.getText().toString());
        jsonParams.put("phone", tv_mobile.getText().toString());
        jsonParams.put("dob", tv_dob.getText().toString());
        jsonParams.put("pan_card", tv_pannu.getText().toString());
        jsonParams.put("account_no", userDetail.getAccountNo());
        jsonParams.put("status_detail", userDetail.getStatusDetail());
        jsonParams.put("is_active", userDetail.getIsActive());
        jsonParams.put("view_password", userDetail.getViewPassword());
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService1().user_update(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(getContext(), "Update Success", Toast.LENGTH_SHORT, true).show();

                } else {
                    Toasty.error(getContext(), "Failed to update.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });


    }
    @OnClick(R.id.civ)
    public void selectProfile(){
        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }


    public String getBase64FromFile(String path) {
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        byte[] baat = null;
        String encodeString = null;
        try {
            bmp = BitmapFactory.decodeFile(path);
            baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baat = baos.toByteArray();
            encodeString = Base64.encodeToString(baat, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodeString;
    }
    public void uploadPic(){

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", userDetail.getUserId());
        jsonParams.put("profile_picture","data:image/jpeg;base64," + SELECT_BASE_64_PATH);

        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService1().uploadProfilePic(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));

                } else {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }


}